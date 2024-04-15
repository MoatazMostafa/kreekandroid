package com.kreek.kreekandroid.data.repository

import com.kreek.kreekandroid.data.firebase.chatmessage.addchatroominfo.AddChatRoomInfoDataSource
import com.kreek.kreekandroid.data.firebase.chatmessage.getchatroominfo.GetChatRoomInfoByIdDataSource
import com.kreek.kreekandroid.data.firebase.chatmessage.getchatroominfo.GetChatRoomInfoDataSource
import com.kreek.kreekandroid.data.firebase.chatmessage.model.ChatMessage
import com.kreek.kreekandroid.data.firebase.chatmessage.model.ChatRoomInfo
import com.kreek.kreekandroid.data.firebase.chatmessage.model.ChatType
import com.kreek.kreekandroid.data.firebase.chatmessage.receivemessages.ReceiveChatMessageDataSource
import com.kreek.kreekandroid.data.firebase.chatmessage.sendmessages.SendChatMessageDataSource
import com.kreek.kreekandroid.data.firebase.doctor.get.GetDoctorByNumberDataSource
import com.kreek.kreekandroid.data.firebase.doctor.get.GetDoctorDataSource
import com.kreek.kreekandroid.data.firebase.doctor.get.GetDoctorListDataSource
import com.kreek.kreekandroid.data.firebase.doctor.model.Doctor
import com.kreek.kreekandroid.data.firebase.doctor.set.SetDoctorDataSource
import com.kreek.kreekandroid.data.firebase.patient.get.GetPatientDataSource
import com.kreek.kreekandroid.data.firebase.patient.model.Patient
import com.kreek.kreekandroid.data.firebase.patient.set.SetPatientDataSource
import com.kreek.kreekandroid.domain.model.DoctorChatDataDomainModel
import com.kreek.kreekandroid.domain.model.toDomainModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class FirebaseRepositoryImpl(
    private val sendChatMessageDataSource: SendChatMessageDataSource,
    private val receiveChatMessageDataSource: ReceiveChatMessageDataSource,
    private val setDoctorDataSource: SetDoctorDataSource,
    private val getDoctorDataSource: GetDoctorDataSource,
    private val getDoctorListDataSource: GetDoctorListDataSource,
    private val addChatRoomInfoDataSource: AddChatRoomInfoDataSource,
    private val getChatRoomInfoDataSource: GetChatRoomInfoDataSource,
    private val getChatRoomInfoByIdDataSource: GetChatRoomInfoByIdDataSource,
    private val getDoctorByNumberDataSource: GetDoctorByNumberDataSource,
    private val getPatientDataSource: GetPatientDataSource,
    private val setPatientDataSource: SetPatientDataSource,
) : FirebaseRepository {

    override suspend fun sendChatMessage(chatMessage: ChatMessage) {
        sendChatMessageDataSource(chatMessage)
    }

    override suspend fun receiveChatMessage(
        chatRoomId: String,
        chatType: ChatType,
        lastMessageTimestamp: Long
    ): Flow<List<ChatMessage>> =
        receiveChatMessageDataSource(chatRoomId, chatType, lastMessageTimestamp)

    override suspend fun getChatRoomInfo(
        userId: String,
        chatType: ChatType
    ): Flow<List<ChatRoomInfo>> =
        getChatRoomInfoDataSource(userId, chatType)

    override suspend fun getChatRoomInfoByRoomId(
        userId: String,
        chatType: ChatType,
        chatRoomId: String
    ): Flow<ChatRoomInfo>  = getChatRoomInfoByIdDataSource(
        userId = userId,
        chatType = chatType,
        chatRoomId = chatRoomId
    )

    override fun addChatRoomInfo(chatRoomInfo: ChatRoomInfo) {
        addChatRoomInfoDataSource(chatRoomInfo)
    }

    override suspend fun setPatient(patient: Patient) {
        setPatientDataSource(patient)
    }

    override suspend fun getPatient(patientId:String): Patient {
        return getPatientDataSource(patientId)
    }

    override fun setDoctor(doctor: Doctor) {
        setDoctorDataSource(doctor)
    }

    override suspend fun getDoctor(doctorId: String): Doctor {
        return getDoctorDataSource(doctorId)
    }

    override suspend fun getDoctorList(): List<Doctor> {
        return getDoctorListDataSource()
    }

    override suspend fun getDoctorByNumber(mobileNumber:String): Doctor? {
        return getDoctorByNumberDataSource(mobileNumber)
    }

    override suspend fun getDoctorChatDataList(userId: String): Flow<List<DoctorChatDataDomainModel>> {
        val doctorChatDomainModelFlow = MutableSharedFlow<List<DoctorChatDataDomainModel>>()
        val job = Job()
        val scope = CoroutineScope(Dispatchers.IO + job)

        scope.launch {
            getChatRoomInfo(userId, ChatType.PRIVATE).collect {
                val doctorChatDomainModelList = mutableListOf<DoctorChatDataDomainModel>()
                it.forEach { chatRoomInfo ->
                    if (userId == chatRoomInfo.userId) {
                        getDoctor(chatRoomInfo.receiverId).let { doctor ->
                            doctorChatDomainModelList.add(
                                DoctorChatDataDomainModel(
                                    chatRoomInfo.toDomainModel(),
                                    doctor.toDomainModel()
                                )
                            )
                        }
                    } else {
                        getDoctor(chatRoomInfo.userId).let { doctor ->
                            doctorChatDomainModelList.add(
                                DoctorChatDataDomainModel(
                                    chatRoomInfo.toDomainModel(),
                                    doctor.toDomainModel()
                                )
                            )
                        }
                    }
                }
                if(doctorChatDomainModelList.isNotEmpty()) {
                    doctorChatDomainModelFlow.emit(doctorChatDomainModelList)
                }
            }
        }
        return doctorChatDomainModelFlow
    }
}