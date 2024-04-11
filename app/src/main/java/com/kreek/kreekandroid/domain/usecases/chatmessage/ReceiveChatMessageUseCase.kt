package com.kreek.kreekandroid.domain.usecases.chatmessage

import com.kreek.kreekandroid.data.firebase.chatmessage.model.ChatMessage
import com.kreek.kreekandroid.data.firebase.chatmessage.model.ChatType
import kotlinx.coroutines.flow.Flow

interface ReceiveChatMessageUseCase {
    suspend fun receiveChatMessage(
        chatRoomId: String,
        chatType: ChatType,
        lastMessageTimestamp: Long
    ): Flow<List<ChatMessage>>
}