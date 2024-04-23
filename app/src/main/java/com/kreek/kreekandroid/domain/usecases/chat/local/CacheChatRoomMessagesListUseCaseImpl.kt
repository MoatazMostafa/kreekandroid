package com.kreek.kreekandroid.domain.usecases.chat.local

import com.kreek.kreekandroid.data.repository.LocalCachedRepository
import com.kreek.kreekandroid.domain.model.ChatRoomMessagesDomainModel

class CacheChatRoomMessagesListUseCaseImpl(
    private val localCachedRepository: LocalCachedRepository
): CacheChatRoomMessagesListUseCase{
    override suspend operator fun invoke(chatRoomMessagesList: List<ChatRoomMessagesDomainModel>){
        localCachedRepository.cacheChatRoomMessagesList(chatRoomMessagesList)
    }
}