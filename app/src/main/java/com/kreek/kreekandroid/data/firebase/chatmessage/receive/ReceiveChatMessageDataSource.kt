package com.kreek.kreekandroid.data.firebase.chatmessage.receive

import com.kreek.kreekandroid.data.firebase.chatmessage.model.ChatMessage
import com.kreek.kreekandroid.data.firebase.chatmessage.model.ChatType
import kotlinx.coroutines.flow.Flow

interface ReceiveChatMessageDataSource {
    suspend operator fun invoke(
        chatRoomId: String,
        chatType: ChatType,
        lastMessageTimestamp: Long
    ): Flow<List<ChatMessage>>
}