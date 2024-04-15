package com.kreek.kreekandroid.domain.usecases.chatmessage.chatroominfo

import com.kreek.kreekandroid.data.repository.LocalCachedRepository
import com.kreek.kreekandroid.domain.model.ChatRoomInfoDomainModel
import com.kreek.kreekandroid.domain.model.toDataModel

class UpdateCachedChatRoomInfoUseCaseImpl(
    private val localCachedRepository: LocalCachedRepository
): UpdateCachedChatRoomInfoUseCase{
    override suspend fun invoke(chatRoomInfoDomainModel: ChatRoomInfoDomainModel) {
        localCachedRepository.updateCachedChatRoomInfo(chatRoomInfoDomainModel.toDataModel())
    }
}