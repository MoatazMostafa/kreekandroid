package com.kreek.kreekandroid.domain.usecases.chat.local

import com.kreek.kreekandroid.data.repository.LocalCachedRepository
import com.kreek.kreekandroid.domain.model.ChatRoomInfoDomainModel

class CacheChatRoomMessagesListUseCaseImpl(
    private val localCachedRepository: LocalCachedRepository
): CacheChatRoomMessagesListUseCase{
    override suspend operator fun invoke(chatRoomInfo: List<ChatRoomInfoDomainModel>){
        localCachedRepository.cacheChatRoomMessagesList(chatRoomInfo)
    }
}