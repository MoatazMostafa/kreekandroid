package com.kreek.kreekandroid.domain.usecases.chat.local

import com.kreek.kreekandroid.domain.model.ChatRoomInfoDomainModel

interface CacheChatRoomMessagesListUseCase {
    suspend operator fun invoke(chatRoomInfo: List<ChatRoomInfoDomainModel>)
}