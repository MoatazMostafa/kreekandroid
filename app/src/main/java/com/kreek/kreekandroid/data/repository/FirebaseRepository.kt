package com.kreek.kreekandroid.data.repository

import com.kreek.kreekandroid.data.firebase.chatmessage.model.ChatMessage
import com.kreek.kreekandroid.data.firebase.chatmessage.model.ChatRoomInfo
import com.kreek.kreekandroid.data.firebase.chatmessage.model.ChatType
import com.kreek.kreekandroid.data.firebase.doctor.model.Doctor
import com.kreek.kreekandroid.data.firebase.patient.model.Patient
import com.kreek.kreekandroid.domain.model.DoctorChatDataDomainModel
import kotlinx.coroutines.flow.Flow

interface FirebaseRepository {
    suspend fun sendChatMessage(chatMessage: ChatMessage)

    suspend fun receiveChatMessage(
        chatRoomId: String,
        chatType: ChatType,
        lastMessageTimestamp: Long
    ): Flow<List<ChatMessage>>

    fun setDoctor(doctor: Doctor)

    fun addChatRoomInfo(chatRoomInfo: ChatRoomInfo)

    suspend fun getChatRoomInfo(userId: String, chatType: ChatType): Flow<List<ChatRoomInfo>>

    suspend fun getChatRoomInfoByRoomId(userId: String, chatType: ChatType, chatRoomId: String): Flow<ChatRoomInfo>

    suspend fun getDoctor(doctorId: String): Doctor

    suspend fun getDoctorList(): List<Doctor>

    suspend fun getDoctorChatDataList(userId:String): Flow<List<DoctorChatDataDomainModel>>

    suspend fun getDoctorByNumber(mobileNumber:String): Doctor?
    suspend fun setPatient(patient: Patient)
    suspend fun getPatient(patientId: String): Patient
}