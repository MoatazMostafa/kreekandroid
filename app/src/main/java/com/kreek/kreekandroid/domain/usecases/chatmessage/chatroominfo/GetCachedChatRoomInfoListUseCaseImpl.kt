package com.kreek.kreekandroid.domain.usecases.chatmessage.chatroominfo

import com.kreek.kreekandroid.data.repository.LocalCachedRepository
import com.kreek.kreekandroid.domain.model.ChatRoomInfoDomainModel
import com.kreek.kreekandroid.domain.model.toDomainModel

class GetCachedChatRoomInfoListUseCaseImpl(
    private val localCachedRepository: LocalCachedRepository
): GetCachedChatRoomInfoListUseCase{
    override suspend fun invoke(): List<ChatRoomInfoDomainModel> {
        return localCachedRepository.getCachedChatRoomInfoList().map { it.toDomainModel() }
    }
}