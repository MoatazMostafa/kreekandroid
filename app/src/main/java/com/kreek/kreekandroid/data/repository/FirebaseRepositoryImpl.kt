package com.kreek.kreekandroid.data.repository

import com.kreek.kreekandroid.data.firebase.chat.sendchatroom.SendChatRoomDataSource
import com.kreek.kreekandroid.data.firebase.chat.receivechatroomslist.ReceiveChatRoomsListDataSource
import com.kreek.kreekandroid.data.firebase.chat.model.ChatMessage
import com.kreek.kreekandroid.data.firebase.chat.model.ChatRoomInfo
import com.kreek.kreekandroid.data.firebase.chat.model.ChatType
import com.kreek.kreekandroid.data.firebase.chat.receivemessages.ReceiveChatMessageDataSource
import com.kreek.kreekandroid.data.firebase.chat.sendmessages.SendChatMessageDataSource
import com.kreek.kreekandroid.data.firebase.doctor.get.GetDoctorByNumberDataSource
import com.kreek.kreekandroid.data.firebase.doctor.get.GetDoctorDataSource
import com.kreek.kreekandroid.data.firebase.doctor.get.GetDoctorListDataSource
import com.kreek.kreekandroid.data.firebase.doctor.model.Doctor
import com.kreek.kreekandroid.data.firebase.doctor.set.SetDoctorDataSource
import com.kreek.kreekandroid.data.firebase.patient.get.GetPatientDataSource
import com.kreek.kreekandroid.data.firebase.patient.model.Patient
import com.kreek.kreekandroid.data.firebase.patient.set.SetPatientDataSource
import kotlinx.coroutines.flow.Flow

class FirebaseRepositoryImpl(
    private val sendChatMessageDataSource: SendChatMessageDataSource,
    private val receiveChatMessageDataSource: ReceiveChatMessageDataSource,
    private val setDoctorDataSource: SetDoctorDataSource,
    private val getDoctorDataSource: GetDoctorDataSource,
    private val getDoctorListDataSource: GetDoctorListDataSource,
    private val sendChatRoomDataSource: SendChatRoomDataSource,
    private val receiveChatRoomsInfoListDataSource: ReceiveChatRoomsListDataSource,
    private val getDoctorByNumberDataSource: GetDoctorByNumberDataSource,
    private val getPatientDataSource: GetPatientDataSource,
    private val setPatientDataSource: SetPatientDataSource,
) : FirebaseRepository {
    override suspend fun setPatient(patient: Patient) {
        setPatientDataSource(patient)
    }

    override suspend fun getPatient(patientId: String): Patient {
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

    override suspend fun getDoctorByNumber(mobileNumber: String): Doctor? {
        return getDoctorByNumberDataSource(mobileNumber)
    }

    override suspend fun sendChatMessage(chatMessage: ChatMessage) {
        sendChatMessageDataSource(chatMessage)
    }

    override suspend fun receiveChatMessage(
        chatRoomId: String,
        chatType: ChatType,
        lastMessageTimestamp: Long
    ): Flow<List<ChatMessage>> {
        return receiveChatMessageDataSource(chatRoomId, chatType, lastMessageTimestamp)
    }

    override suspend fun sendChatRoom(chatRoomInfo: ChatRoomInfo) {
        sendChatRoomDataSource(chatRoomInfo)
    }

    override suspend fun receiveChatRoomsInfoList(
        userId: String,
        chatType: ChatType
    ): Flow<List<ChatRoomInfo>> {
        return receiveChatRoomsInfoListDataSource(userId, chatType)
    }
}