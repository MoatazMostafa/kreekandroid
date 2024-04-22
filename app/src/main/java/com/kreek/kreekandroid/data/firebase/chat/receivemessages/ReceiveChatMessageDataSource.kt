package com.kreek.kreekandroid.data.firebase.chat.receivemessages

import com.kreek.kreekandroid.data.firebase.chat.model.ChatMessage
import com.kreek.kreekandroid.data.firebase.chat.model.ChatType
import kotlinx.coroutines.flow.Flow

interface ReceiveChatMessageDataSource {
    suspend operator fun invoke(
        chatRoomId: String,
        chatType: ChatType,
        lastMessageTimestamp: Long
    ): Flow<List<ChatMessage>>
}