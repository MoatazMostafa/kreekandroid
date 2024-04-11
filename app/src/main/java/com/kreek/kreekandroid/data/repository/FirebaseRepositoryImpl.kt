package com.kreek.kreekandroid.data.repository

import com.kreek.kreekandroid.data.firebase.chatmessage.model.ChatMessage
import com.kreek.kreekandroid.data.firebase.chatmessage.model.ChatType
import com.kreek.kreekandroid.data.firebase.chatmessage.receive.ReceiveChatMessageDataSource
import com.kreek.kreekandroid.data.firebase.chatmessage.send.SendChatMessageDataSource
import com.kreek.kreekandroid.data.firebase.doctor.create.CreateDoctorDataSource
import com.kreek.kreekandroid.data.firebase.doctor.get.GetDoctorDataSource
import com.kreek.kreekandroid.data.firebase.doctor.model.Doctor
import kotlinx.coroutines.flow.Flow

class FirebaseRepositoryImpl(
    private val sendChatMessageDataSource: SendChatMessageDataSource,
    private val receiveChatMessageDataSource: ReceiveChatMessageDataSource,
    private val createDoctorDataSource: CreateDoctorDataSource,
    private val getDoctorDataSource: GetDoctorDataSource
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

    override fun createDoctor(doctor: Doctor) {
        createDoctorDataSource(doctor)
    }

    override suspend fun getDoctor(doctorId: String): Doctor {
        return getDoctorDataSource(doctorId)
    }
}