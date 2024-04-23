package com.kreek.kreekandroid.domain.usecases.chat.local

import com.kreek.kreekandroid.data.repository.LocalCachedRepository
import com.kreek.kreekandroid.domain.model.ChatRoomInfoDomainModel

class CacheChatRoomInfoListUseCaseImpl(
    private val localCachedRepository: LocalCachedRepository
): CacheChatRoomInfoListUseCase{
    override suspend operator fun invoke(chatRoomInfoList: List<ChatRoomInfoDomainModel>){
        localCachedRepository.cacheChatRoomInfoList(chatRoomInfoList)
    }
}