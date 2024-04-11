package com.kreek.kreekandroid.ui.features.chatroom

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.viewModelScope
import com.kreek.kreekandroid.common.manager.navigation.KreekNavDestination
import com.kreek.kreekandroid.data.firebase.chatmessage.model.ChatType
import com.kreek.kreekandroid.data.vectara.model.VectaraQueryBody
import com.kreek.kreekandroid.domain.usecases.chatmessage.ReceiveChatMessageUseCase
import com.kreek.kreekandroid.domain.usecases.chatmessage.SendChatMessageUseCase
import com.kreek.kreekandroid.domain.usecases.lastmessagetimestamp.CacheLastMessageTimestampUseCase
import com.kreek.kreekandroid.domain.usecases.lastmessagetimestamp.GetCachedLastMessageTimestampUseCase
import com.kreek.kreekandroid.domain.usecases.vectara.GetVectaraQueryResponseUseCase
import com.kreek.kreekandroid.ui.features.chatroom.model.ChatMessageUIModel
import com.kreek.kreekandroid.ui.features.chatroom.model.ChatRoomType
import com.kreek.kreekandroid.ui.features.chatroom.model.toDomainModel
import com.kreek.kreekandroid.ui.features.chatroom.model.toUIModel
import com.kreek.kreekandroid.ui.shared.base.BaseViewModel
import com.kreek.kreekandroid.ui.shared.uimodel.PatientUIModel
import com.kreek.kreekandroid.ui.shared.uimodel.getMockPatient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ChatRoomViewModel(
    application: Application,
    backStackEntryArguments: Bundle?,
    private val sendChatMessageUseCase: SendChatMessageUseCase,
    private val receiveChatMessageUseCase: ReceiveChatMessageUseCase,
    private val cacheLastMessageTimestampUseCase: CacheLastMessageTimestampUseCase,
    private val getCachedLastMessageTimestampUseCase: GetCachedLastMessageTimestampUseCase,
    private val getVectaraQueryResponseUseCase: GetVectaraQueryResponseUseCase
) : BaseViewModel(
    application = application,
) {
    private val _chatRoomArguments =
        KreekNavDestination.ChatRoom.parseArguments(backStackEntryArguments)

    private val _patient = MutableStateFlow<PatientUIModel>(getMockPatient())
    var patient = _patient.asStateFlow()

    private val _chatMessages = MutableStateFlow<List<ChatMessageUIModel>>(emptyList())
    var chatMessages = _chatMessages.asStateFlow()

    val userId = "1"

    var chatRoomType: ChatRoomType = ChatRoomType.fromString(_chatRoomArguments.chatRoomType)

    init {
        viewModelScope.launch {
            if (chatRoomType != ChatRoomType.CHAT_BOT) {
                fetchChatMessages(/*_chatRoomArguments.chatRoomId*/"1234567")
            }
        }

    }

    private suspend fun fetchChatMessages(chatRoomId: String) {
        receiveChatMessageUseCase.receiveChatMessage(
            chatRoomId,
            ChatType.GROUP,
            getCachedLastMessageTimestampUseCase.getLastMessageTimestamp()
        ).collect {
            val tmpList = _chatMessages.value.toMutableList()
            tmpList.addAll(it.map { chatMessage -> chatMessage.toUIModel() })
            _chatMessages.value = tmpList
            cacheLastMessageTimestampUseCase.saveLastMessageTimestamp(it.last().timestamp)
        }
    }

    fun sendMessage(message: String) {
        viewModelScope.launch {
            if (chatRoomType != ChatRoomType.CHAT_BOT) {
                sendChatMessageUseCase.sendChatMessage(
                    ChatMessageUIModel(
                        message = message,
                        senderId = "1",
                        receiverId = "6AYDvVw3hdY1pX5z2boKXffu70D2",
                        chatRoomId = "1234567",
                        timestamp = System.currentTimeMillis(),
                        chatType = ChatType.GROUP
                    ).toDomainModel()
                )
            } else {
                var tmpList = _chatMessages.value.toMutableList()
                tmpList.add(
                    ChatMessageUIModel(
                        senderId = "1",
                        senderName = "",
                        message = message,
                        receiverId = "",
                        patientId = "",
                        timestamp = 0L,
                        chatRoomId = ""
                    )
                )
                _chatMessages.value = tmpList
                val x = getVectaraQueryResponseUseCase(
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
                tmpList = _chatMessages.value.toMutableList()
                tmpList.add(
                    ChatMessageUIModel(
                        senderId = "2",
                        senderName = "",
                        message = x?.responseSet?.first()?.summary?.first()?.text ?: "",
                        receiverId = "",
                        patientId = "",
                        timestamp = 0L,
                        chatRoomId = ""
                    )
                )
                _chatMessages.value = tmpList
            }
        }
    }

    fun onPatientInfoClicked() {
        navController?.navigate(
            route =
            KreekNavDestination.PatientInfo.getNavigationRoute(patientId = _patient.value.patientId).route
        )
    }
}