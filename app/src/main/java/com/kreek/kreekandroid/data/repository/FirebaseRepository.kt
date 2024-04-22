package com.kreek.kreekandroid.data.repository

import com.kreek.kreekandroid.data.firebase.chat.model.ChatMessage
import com.kreek.kreekandroid.data.firebase.chat.model.ChatRoomInfo
import com.kreek.kreekandroid.data.firebase.chat.model.ChatType
import com.kreek.kreekandroid.data.firebase.doctor.model.Doctor
import com.kreek.kreekandroid.data.firebase.patient.model.Patient
import kotlinx.coroutines.flow.Flow

interface FirebaseRepository {
    fun setDoctor(doctor: Doctor)
    suspend fun getDoctor(doctorId: String): Doctor
    suspend fun getDoctorList(): List<Doctor>
    suspend fun getDoctorByNumber(mobileNumber: String): Doctor?
    ////
    suspend fun setPatient(patient: Patient)
    suspend fun getPatient(patientId: String): Patient
    ////
    suspend fun sendChatMessage(chatMessage: ChatMessage)
    suspend fun receiveChatMessage(
        chatRoomId: String,
        chatType: ChatType,
        lastMessageTimestamp: Long
    ): Flow<List<ChatMessage>>
    suspend fun sendChatRoom(chatRoomInfo: ChatRoomInfo)
    suspend fun receiveChatRoomsInfoList(userId: String, chatType: ChatType): Flow<List<ChatRoomInfo>>
}