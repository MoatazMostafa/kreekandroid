package com.kreek.kreekandroid.domain.usecases.chat.local

import com.kreek.kreekandroid.data.repository.LocalCachedRepository
import com.kreek.kreekandroid.domain.model.ChatRoomMessagesDomainModel
import com.kreek.kreekandroid.domain.model.toDataModel

class CacheChatRoomMessagesUseCaseImpl(
    private val localCachedRepository: LocalCachedRepository
): CacheChatRoomMessagesUseCase{
    override suspend operator fun invoke(chatRoomMessages: ChatRoomMessagesDomainModel){
        localCachedRepository.cacheChatRoomMessages(chatRoomMessages.toDataModel())
    }
}