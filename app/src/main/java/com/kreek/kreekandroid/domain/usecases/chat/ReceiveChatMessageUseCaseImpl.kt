package com.kreek.kreekandroid.domain.usecases.chat

import com.kreek.kreekandroid.data.firebase.chat.model.ChatMessage
import com.kreek.kreekandroid.data.firebase.chat.model.ChatType
import com.kreek.kreekandroid.data.repository.FirebaseRepository
import kotlinx.coroutines.flow.Flow

class ReceiveChatMessageUseCaseImpl(
    private val firebaseChatRepository: FirebaseRepository
): ReceiveChatMessageUseCase{
    override suspend fun invoke(
        chatRoomId: String,
        chatType: ChatType,
        lastMessageTimestamp: Long
    ): Flow<List<ChatMessage>> {
        return firebaseChatRepository.receiveChatMessage(
            chatRoomId,
            chatType,
            lastMessageTimestamp
        )
    }
}