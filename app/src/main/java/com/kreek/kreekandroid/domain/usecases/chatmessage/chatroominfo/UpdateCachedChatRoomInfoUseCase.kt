package com.kreek.kreekandroid.domain.usecases.chatmessage.chatroominfo

import com.kreek.kreekandroid.domain.model.ChatRoomInfoDomainModel

interface UpdateCachedChatRoomInfoUseCase {
    suspend operator fun invoke(chatRoomInfoDomainModel: ChatRoomInfoDomainModel)
}