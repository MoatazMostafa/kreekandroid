package com.kreek.kreekandroid.ui.features.chatroom

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.viewModelScope
import com.kreek.kreekandroid.common.manager.navigation.KreekNavDestination
import com.kreek.kreekandroid.data.firebase.chat.model.ChatMessageType
import com.kreek.kreekandroid.data.firebase.chat.model.ChatType
import com.kreek.kreekandroid.domain.model.toDomainModel
import com.kreek.kreekandroid.domain.usecases.chat.ReceiveChatMessageUseCase
import com.kreek.kreekandroid.domain.usecases.chat.SendChatMessageUseCase
import com.kreek.kreekandroid.domain.usecases.chat.local.GetCachedChatRoomMessagesUseCase
import com.kreek.kreekandroid.domain.usecases.chat.local.UpdateCachedChatRoomMessages
import com.kreek.kreekandroid.domain.usecases.doctor.GetCachedDoctorUseCase
import com.kreek.kreekandroid.domain.usecases.vectara.GetVectaraQueryResponseUseCase
import com.kreek.kreekandroid.ui.shared.base.BaseViewModel
import com.kreek.kreekandroid.ui.shared.uimodel.ChatMessageUIModel
import com.kreek.kreekandroid.ui.shared.uimodel.ChatRoomMessagesUIModel
import com.kreek.kreekandroid.ui.shared.uimodel.DoctorUIModel
import com.kreek.kreekandroid.ui.shared.uimodel.toDomainModel
import com.kreek.kreekandroid.ui.shared.uimodel.toUIModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ChatRoomViewModel(
    application: Application,
    backStackEntryArguments: Bundle?,
    private val sendChatMessageUseCase: SendChatMessageUseCase,
    private val receiveChatMessageUseCase: ReceiveChatMessageUseCase,
    private val getCachedChatRoomMessagesUseCase: GetCachedChatRoomMessagesUseCase,
    private val updateCachedChatRoomMessages: UpdateCachedChatRoomMessages,
    private val getVectaraQueryResponseUseCase: GetVectaraQueryResponseUseCase,
    private val getCachedDoctorUseCase: GetCachedDoctorUseCase,
) : BaseViewModel(
    application = application,
) {
    private val _chatRoomArguments =
        KreekNavDestination.ChatRoom.parseArguments(backStackEntryArguments)

    private val _chatRoomMessages = MutableStateFlow(ChatRoomMessagesUIModel())
    var chatRoomMessages = _chatRoomMessages.asStateFlow()

    val chatRoomId: String = _chatRoomArguments.chatRoomId

    private val _userDoctor = MutableStateFlow(DoctorUIModel())
    var userDoctor = _userDoctor.asStateFlow()


    init {
        viewModelScope.launch {
            _chatRoomMessages.value = getCachedChatRoomMessagesUseCase(chatRoomId)?.toUIModel()
                ?: ChatRoomMessagesUIModel()
            receiveChatMessageUseCase(
                chatRoomId,
                _chatRoomMessages.value.chatType,
                chatRoomMessages.value.lastMessageTimestamp
            ).collect {
                val x = updateCachedChatRoomMessages(
                    chatRoomId = chatRoomId,
                    lastMessage = it.lastOrNull()?.message,
                    lastMessageTimestamp = it.lastOrNull()?.timestamp,
                    numberOfUnreadMessages = 0,
                    chatMessageList = it.map { it.toDomainModel() }
                ).toUIModel()

                _chatRoomMessages.value = x
            }
        }
    }

    fun sendMessage(message: String) {
        viewModelScope.launch {
            when (_chatRoomMessages.value.chatType) {
                ChatType.PRIVATE -> sendMessageGroup(message)
                ChatType.GROUP -> sendMessageGroup(message)
                ChatType.VECTARA_CHAT_BOT -> sendMessageVectaraChatBot(message)
            }
//
//            if (chatType != ChatType.VECTARA_CHAT_BOT) {
//
//            } else {
//                var tmpList = _chatMessages.value.toMutableList()
//                tmpList.add(
//                    ChatMessageUIModel(
//                        senderId = "1",
//                        senderName = "",
//                        message = message,
//                        receiverId = "",
//                        patientId = "",
//                        timestamp = 0L,
//                        chatRoomId = ""
//                    )
//                )
//                _chatMessages.value = tmpList
//                val x = getVectaraQueryResponseUseCase(
//                    VectaraQueryBody(
//                        listOf(
//                            VectaraQueryBody.Query(
//                                query = message,
//                                start = 0,
//                                numResults = 10,
//                                corpusKey = listOf(
//                                    VectaraQueryBody.Query.CorpusKey(
//                                        corpusId = 2
//                                    )
//                                ),
//                                summary = listOf(
//                                    VectaraQueryBody.Query.Summary(
//                                        maxSummarizedResults = 10,
//                                        responseLang = "en"
//                                    )
//                                )
//                            )
//                        )
//                    )
//                )
//                tmpList = _chatMessages.value.toMutableList()
//                tmpList.add(
//                    ChatMessageUIModel(
//                        senderId = "2",
//                        senderName = "",
//                        message = x?.responseSet?.first()?.summary?.first()?.text ?: "",
//                        receiverId = "",
//                        patientId = "",
//                        timestamp = 0L,
//                        chatRoomId = ""
//                    )
//                )
//                _chatMessages.value = tmpList
//            }
        }
    }

    private fun sendMessageVectaraChatBot(message: String) {
        //TODO("Not yet implemented")
    }

    private suspend fun sendMessageGroup(message: String) {
        sendChatMessageUseCase.sendChatMessage(
            ChatMessageUIModel(
                chatRoomId = chatRoomId,
                senderId = userDoctor.value.id,
                receiverId = if (_chatRoomMessages.value.firstUserId == userDoctor.value.id)
                    _chatRoomMessages.value.secondUserId
                else
                    _chatRoomMessages.value.firstUserId,
                firstUserId = _chatRoomMessages.value.firstUserId,
                secondUserId = _chatRoomMessages.value.secondUserId,
                firstUserName = _chatRoomMessages.value.firstUserName,
                secondUserName = _chatRoomMessages.value.secondUserName,
                patientId = _chatRoomMessages.value.patientId,
                patientName = _chatRoomMessages.value.patientName,
                message = message,
                timestamp = System.currentTimeMillis(),
                messageType = ChatMessageType.TEXT,
                chatType = _chatRoomMessages.value.chatType,
            ).toDomainModel()
        )
    }

    fun onPatientInfoClicked() {
        navController?.navigate(
            route =
            KreekNavDestination.PatientInfo.getNavigationRoute(
                patientId = _chatRoomMessages.value.patientId ?: ""
            ).route
        )
    }
}