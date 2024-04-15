package com.kreek.kreekandroid.domain.usecases.chatmessage.chatroominfo

import com.kreek.kreekandroid.data.firebase.chatmessage.model.ChatRoomInfo
import com.kreek.kreekandroid.data.firebase.chatmessage.model.ChatType
import com.kreek.kreekandroid.data.repository.FirebaseRepository
import kotlinx.coroutines.flow.Flow

class GetChatDataByRoomIdUseCaseImpl(
    private val firebaseRepository: FirebaseRepository
) : GetChatDataByRoomIdUseCase {
    override suspend fun invoke(
        userId: String,
        chatType: ChatType,
        chatRoomId: String
    ): Flow<ChatRoomInfo> {
        return firebaseRepository.getChatRoomInfoByRoomId(userId, chatType, chatRoomId)
    }
}