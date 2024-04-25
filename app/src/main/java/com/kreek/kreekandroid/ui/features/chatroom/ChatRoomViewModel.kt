package com.kreek.kreekandroid.ui.features.chatroom

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.viewModelScope
import com.kreek.kreekandroid.common.manager.navigation.KreekNavDestination
import com.kreek.kreekandroid.data.firebase.chat.model.ChatMessageType
import com.kreek.kreekandroid.data.firebase.chat.model.ChatType
import com.kreek.kreekandroid.data.vectara.model.VectaraQueryBody
import com.kreek.kreekandroid.domain.usecases.chat.SendChatMessageUseCase
import com.kreek.kreekandroid.domain.usecases.chat.local.GetCachedChatRoomMessagesListFlowUseCase
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
    private val getCachedChatRoomMessagesUseCase: GetCachedChatRoomMessagesUseCase,
    private val updateCachedChatRoomMessages: UpdateCachedChatRoomMessages,
    private val getVectaraQueryResponseUseCase: GetVectaraQueryResponseUseCase,
    private val getCachedDoctorUseCase: GetCachedDoctorUseCase,
    private val getCachedChatRoomMessagesListFlow: GetCachedChatRoomMessagesListFlowUseCase
) : BaseViewModel(
    application = application,
) {
    private val _chatRoomArguments =
        KreekNavDestination.ChatRoom.parseArguments(backStackEntryArguments)

    private val _chatRoomMessages = MutableStateFlow(ChatRoomMessagesUIModel())
    var chatRoomMessages = _chatRoomMessages.asStateFlow()

    val chatRoomId: String = _chatRoomArguments.chatRoomId
    val chatType: ChatType = ChatType.fromString(_chatRoomArguments.chatType)

    private val _userDoctor = MutableStateFlow(DoctorUIModel())
    var userDoctor = _userDoctor.asStateFlow()


    init {
        fetchChatMessages()
    }

    private fun fetchChatMessages() {
        viewModelScope.launch {
            _userDoctor.value = getCachedDoctorUseCase()?.toUIModel() ?: DoctorUIModel()
            _chatRoomMessages.value = getCachedChatRoomMessagesUseCase(chatRoomId)?.toUIModel() ?: ChatRoomMessagesUIModel()
            _chatRoomMessages.value = updateCachedChatRoomMessages(
                chatRoomId = chatRoomId,
                numberOfUnreadMessages = 0
            ).toUIModel()
            getCachedChatRoomMessagesListFlow.invoke().collect {
                _chatRoomMessages.value = it.first { it.chatRoomId == chatRoomId }.toUIModel()
            }
        }
    }

    fun sendMessage(message: String) {
        viewModelScope.launch {
            when (chatType) {
                ChatType.PRIVATE -> sendMessageNormal(message)
                ChatType.GROUP -> sendMessageNormal(message)
                ChatType.VECTARA_CHAT_BOT -> sendMessageVectaraChatBot(message)
            }
        }
    }

    private fun sendMessageVectaraChatBot(message: String) {
        viewModelScope.launch {
            var chatMessageUIModel = ChatMessageUIModel(
                senderId = userDoctor.value.id,
                receiverId = _chatRoomMessages.value.secondUserId,
                message = message,
                timestamp = System.currentTimeMillis(),
                chatType = chatType,
                chatRoomId = chatRoomId,
            )
            _chatRoomMessages.value = updateCachedChatRoomMessages(
                chatRoomId = chatRoomId,
                numberOfUnreadMessages = 0,
                chatMessageList = listOf(chatMessageUIModel.toDomainModel())
            ).toUIModel()
            sendChatMessageUseCase.sendChatMessage(chatMessageUIModel.toDomainModel())
            val vectaraQueryResponse = getVectaraQueryResponseUseCase(
                VectaraQueryBody(
                    listOf(
                        VectaraQueryBody.Query(
                            query = message,
                            start = 0,
                            numResults = 10,
                            corpusKey = listOf(
                                VectaraQueryBody.Query.CorpusKey(
                                    corpusId = 2
                                )
                            ),
                            summary = listOf(
                                VectaraQueryBody.Query.Summary(
                                    maxSummarizedResults = 10,
                                    responseLang = "en"
                                )
                            )
                        )
                    )
                )
            )
            chatMessageUIModel = ChatMessageUIModel(
                senderId = _chatRoomMessages.value.secondUserId,
                receiverId = userDoctor.value.id,
                message = vectaraQueryResponse?.responseSet?.first()?.summary?.first()?.text ?: "",
                timestamp = System.currentTimeMillis(),
                chatType = chatType,
                chatRoomId = chatRoomId,
            )
            _chatRoomMessages.value = updateCachedChatRoomMessages(
                chatRoomId = chatRoomId,
                numberOfUnreadMessages = 0,
                chatMessageList = listOf(chatMessageUIModel.toDomainModel())
            ).toUIModel()
            sendChatMessageUseCase.sendChatMessage(chatMessageUIModel.toDomainModel())
        }

    }

    private suspend fun sendMessageNormal(message: String) {
        sendChatMessageUseCase.sendChatMessage(
            ChatMessageUIModel(
                chatRoomId = chatRoomId,
                senderId = userDoctor.value.id,
                receiverId = if (_chatRoomMessages.value.firstUserId == userDoctor.value.id)
                    _chatRoomMessages.value.secondUserId
                else
                    _chatRoomMessages.value.firstUserId,
                message = message,
                timestamp = System.currentTimeMillis(),
                messageType = ChatMessageType.TEXT,
                chatType = _chatRoomMessages.value.chatType,
            ).toDomainModel()
        )
    }

    fun onPatientInfoClicked() {
//        navController?.navigate(
//            route =
//            KreekNavDestination.PatientInfo.getNavigationRoute(
//                patientId = _chatRoomMessages.value.patientId ?: ""
//            ).route
//        )
    }
}