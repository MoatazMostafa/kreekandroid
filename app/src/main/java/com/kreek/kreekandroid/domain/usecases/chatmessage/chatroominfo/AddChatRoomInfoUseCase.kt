package com.kreek.kreekandroid.domain.usecases.chatmessage.chatroominfo

import com.kreek.kreekandroid.domain.model.ChatRoomInfoDomainModel

interface AddChatRoomInfoUseCase {
    operator fun invoke(chatRoomInfo: ChatRoomInfoDomainModel)
}