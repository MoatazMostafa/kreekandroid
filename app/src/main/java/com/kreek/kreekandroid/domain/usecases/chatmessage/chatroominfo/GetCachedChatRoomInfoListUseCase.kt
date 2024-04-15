package com.kreek.kreekandroid.domain.usecases.chatmessage.chatroominfo

import com.kreek.kreekandroid.domain.model.ChatRoomInfoDomainModel

interface GetCachedChatRoomInfoListUseCase {
    suspend operator fun invoke(): List<ChatRoomInfoDomainModel>
}