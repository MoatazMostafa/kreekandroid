package com.kreek.kreekandroid.domain.usecases.chat.local

import com.kreek.kreekandroid.data.repository.LocalCachedRepository
import com.kreek.kreekandroid.domain.model.ChatRoomMessagesDomainModel

class GetCachedChatRoomMessagesUseCaseImpl(
    private val localCachedRepository: LocalCachedRepository
): GetCachedChatRoomMessagesUseCase{
    override suspend fun invoke(chatRoomId: String): ChatRoomMessagesDomainModel? {
        return localCachedRepository.getCachedChatRoomMessages(chatRoomId)
    }
}