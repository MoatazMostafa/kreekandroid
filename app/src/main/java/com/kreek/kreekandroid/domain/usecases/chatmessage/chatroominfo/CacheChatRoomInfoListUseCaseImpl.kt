package com.kreek.kreekandroid.domain.usecases.chatmessage.chatroominfo

import com.kreek.kreekandroid.data.repository.LocalCachedRepository
import com.kreek.kreekandroid.domain.model.ChatRoomInfoDomainModel
import com.kreek.kreekandroid.domain.model.toDataModel

class CacheChatRoomInfoListUseCaseImpl(
    private val localCachedRepository: LocalCachedRepository
): CacheChatRoomInfoListUseCase{
    override suspend fun invoke(chatRoomInfoDomainModelList: List<ChatRoomInfoDomainModel>) {
        localCachedRepository.cacheChatRoomInfoList(chatRoomInfoDomainModelList.map { it.toDataModel() })
    }

}