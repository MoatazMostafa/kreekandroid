package com.kreek.kreekandroid.domain.usecases.chat

import com.kreek.kreekandroid.data.firebase.chat.model.ChatRoomInfo
import com.kreek.kreekandroid.data.firebase.chat.model.ChatType
import com.kreek.kreekandroid.data.repository.FirebaseRepository
import kotlinx.coroutines.flow.Flow

class ReceiveChatRoomsInfoListUseCaseImpl(
    private val firebaseChatRepository: FirebaseRepository
): ReceiveChatRoomsInfoListUseCase{
    override suspend fun invoke(
        userId: String,
        chatType: ChatType
    ): Flow<List<ChatRoomInfo>> {
        return firebaseChatRepository.receiveChatRoomsInfoList(userId, chatType)
    }
}