package com.kreek.kreekandroid.domain.usecases.chat

import com.kreek.kreekandroid.domain.model.ChatRoomInfoDomainModel

interface SendChatRoomUseCase {
    suspend operator fun invoke(chatRoomInfo: ChatRoomInfoDomainModel)
}