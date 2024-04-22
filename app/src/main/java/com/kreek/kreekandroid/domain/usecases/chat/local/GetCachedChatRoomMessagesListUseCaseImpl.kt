package com.kreek.kreekandroid.domain.usecases.chat.local

import com.kreek.kreekandroid.data.repository.LocalCachedRepository
import com.kreek.kreekandroid.domain.model.ChatRoomMessagesDomainModel
import com.kreek.kreekandroid.domain.model.toDomainModel

class GetCachedChatRoomMessagesListUseCaseImpl(
    private val localCachedRepository: LocalCachedRepository
): GetCachedChatRoomMessagesListUseCase{
    override suspend operator fun invoke(): List<ChatRoomMessagesDomainModel>{
        return localCachedRepository.getCachedChatRoomMessagesList().map { it.toDomainModel() }
    }
}