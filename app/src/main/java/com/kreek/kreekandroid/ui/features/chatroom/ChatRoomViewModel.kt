package com.kreek.kreekandroid.ui.features.chatroom

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.viewModelScope
import com.kreek.kreekandroid.common.manager.navigation.KreekNavDestination
import com.kreek.kreekandroid.data.firebase.chatmessage.model.ChatType
import com.kreek.kreekandroid.data.vectara.model.VectaraQueryBody
import com.kreek.kreekandroid.domain.usecases.chatmessage.ReceiveChatMessageUseCase
import com.kreek.kreekandroid.domain.usecases.chatmessage.SendChatMessageUseCase
import com.kreek.kreekandroid.domain.usecases.chatmessage.chatroominfo.GetChatDataByRoomIdUseCase
import com.kreek.kreekandroid.domain.usecases.doctor.GetCachedDoctorUseCase
import com.kreek.kreekandroid.domain.usecases.doctor.GetDoctorUseCase
import com.kreek.kreekandroid.domain.usecases.lastmessagetimestamp.CacheLastMessageTimestampUseCase
import com.kreek.kreekandroid.domain.usecases.lastmessagetimestamp.GetCachedLastMessageTimestampUseCase
import com.kreek.kreekandroid.domain.usecases.patient.GetPatientUseCase
import com.kreek.kreekandroid.domain.usecases.vectara.GetVectaraQueryResponseUseCase
import com.kreek.kreekandroid.ui.features.chatroom.model.ChatMessageUIModel
import com.kreek.kreekandroid.ui.features.chatroom.model.toDomainModel
import com.kreek.kreekandroid.ui.features.chatroom.model.toUIModel
import com.kreek.kreekandroid.ui.shared.base.BaseViewModel
import com.kreek.kreekandroid.ui.shared.uimodel.ChatRoomInfoUIModel
import com.kreek.kreekandroid.ui.shared.uimodel.DoctorUIModel
import com.kreek.kreekandroid.ui.shared.uimodel.PatientUIModel
import com.kreek.kreekandroid.ui.shared.uimodel.toUIModel
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
    private val getVectaraQueryResponseUseCase: GetVectaraQueryResponseUseCase,
    private val getCachedDoctorUseCase: GetCachedDoctorUseCase,
    private val getChatDataByRoomIdUseCase: GetChatDataByRoomIdUseCase,
    private val getDoctorUseCase: GetDoctorUseCase,
    private val getPatientUseCase: GetPatientUseCase
) : BaseViewModel(
    application = application,
) {
    private val _chatRoomArguments =
        KreekNavDestination.ChatRoom.parseArguments(backStackEntryArguments)

    private val _chatMessages = MutableStateFlow<List<ChatMessageUIModel>>(emptyList())
    var chatMessages = _chatMessages.asStateFlow()

    private var chatRoomInfo = ChatRoomInfoUIModel()

    val chatType: ChatType = ChatType.fromString(_chatRoomArguments.chatType)
    val chatRoomId: String = _chatRoomArguments.chatRoomId
    private var userDoctor: DoctorUIModel? = null
    var doctorReceiver: DoctorUIModel? = null
    var patient: PatientUIModel? = null
    var senderId:String = ""

    init {
        viewModelScope.launch {
            userDoctor = getCachedDoctorUseCase()?.toUIModel()
            senderId = userDoctor?.id?:""
            if (chatType != ChatType.VECTARA_CHAT_BOT) {
                fetchChatMessages()
            }

        }
        viewModelScope.launch {
            fetchChatRoomInfo()
        }

    }

    private suspend fun fetchChatRoomInfo() {
        getChatDataByRoomIdUseCase(
            userId = userDoctor?.id ?: "",
            chatType = chatType,
            chatRoomId = chatRoomId
        ).collect {
            chatRoomInfo = it.toUIModel()
            when (chatType) {
                ChatType.PRIVATE -> {
                    doctorReceiver = getDoctorUseCase(it.receiverId).toUIModel()
                    val x = getDoctorUseCase(it.receiverId).toUIModel()

                }

                ChatType.GROUP -> {
                    patient = getPatientUseCase(it.patientId).toUIModel()
                }

                ChatType.VECTARA_CHAT_BOT -> {

                }
            }
        }
    }

    private suspend fun fetchChatMessages() {
        receiveChatMessageUseCase.receiveChatMessage(
            chatRoomId,
            chatType,
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
            when (chatType) {
                ChatType.PRIVATE -> sendMessagePrivate(message)
                ChatType.GROUP -> sendMessageGroup(message)
                ChatType.VECTARA_CHAT_BOT -> sendMessageVectaraChatBot(message)
            }

            if (chatType != ChatType.VECTARA_CHAT_BOT) {

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

    private fun sendMessageVectaraChatBot(message: String) {
        //TODO("Not yet implemented")
    }

    private suspend fun sendMessageGroup(message: String) {
        sendChatMessageUseCase.sendChatMessage(
            ChatMessageUIModel(
                message = message,
                senderId = userDoctor?.id ?: "",
                patientId = patient?.patientData?.id ?: "",
                chatRoomId = chatRoomId,
                timestamp = System.currentTimeMillis(),
                chatType = ChatType.GROUP
            ).toDomainModel()
        )
    }

    private suspend fun sendMessagePrivate(message: String) {
        sendChatMessageUseCase.sendChatMessage(
            ChatMessageUIModel(
                message = message,
                senderId = userDoctor?.id ?: "",
                receiverId = doctorReceiver?.id ?: "",
                chatRoomId = chatRoomId,
                timestamp = System.currentTimeMillis(),
                chatType = ChatType.PRIVATE
            ).toDomainModel()
        )
    }

    fun onPatientInfoClicked() {
        navController?.navigate(
            route =
            KreekNavDestination.PatientInfo.getNavigationRoute(
                patientId = patient?.patientData?.id ?: ""
            ).route
        )
    }
}