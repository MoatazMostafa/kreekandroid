package com.kreek.kreekandroid.data.repository

import com.kreek.kreekandroid.data.firebase.chatmessage.model.ChatMessage
import com.kreek.kreekandroid.data.firebase.chatmessage.model.ChatType
import com.kreek.kreekandroid.data.firebase.doctor.model.Doctor
import kotlinx.coroutines.flow.Flow

interface FirebaseRepository {
    suspend fun sendChatMessage(chatMessage: ChatMessage)

    suspend fun receiveChatMessage(
        chatRoomId: String,
        chatType: ChatType,
        lastMessageTimestamp: Long
    ): Flow<List<ChatMessage>>

    suspend fun getDoctor(doctorId: String): Doctor
    fun createDoctor(doctor: Doctor)
}