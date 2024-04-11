package com.kreek.kreekandroid.domain.usecases.chatmessage

import com.kreek.kreekandroid.data.firebase.chatmessage.model.ChatMessage
import com.kreek.kreekandroid.data.firebase.chatmessage.model.ChatType
import com.kreek.kreekandroid.data.repository.FirebaseRepository
import kotlinx.coroutines.flow.Flow

class ReceiveChatMessageUseCaseImpl(
    private val firebaseChatRepository: FirebaseRepository
) : ReceiveChatMessageUseCase {
    override suspend fun receiveChatMessage(
        chatRoomId: String,
        chatType: ChatType,
        lastMessageTimestamp: Long
    ): Flow<List<ChatMessage>> =
        firebaseChatRepository.receiveChatMessage(chatRoomId, chatType, lastMessageTimestamp)
}