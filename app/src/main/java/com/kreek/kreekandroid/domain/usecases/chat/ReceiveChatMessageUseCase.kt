package com.kreek.kreekandroid.domain.usecases.chat

import com.kreek.kreekandroid.data.firebase.chat.model.ChatMessage
import com.kreek.kreekandroid.data.firebase.chat.model.ChatType
import kotlinx.coroutines.flow.Flow

interface ReceiveChatMessageUseCase {
    suspend operator fun invoke(
        chatRoomId: String,
        chatType: ChatType,
        lastMessageTimestamp: Long
    ): Flow<List<ChatMessage>>
}