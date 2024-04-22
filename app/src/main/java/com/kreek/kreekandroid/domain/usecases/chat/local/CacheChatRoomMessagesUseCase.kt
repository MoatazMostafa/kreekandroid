package com.kreek.kreekandroid.domain.usecases.chat.local

import com.kreek.kreekandroid.domain.model.ChatRoomMessagesDomainModel

interface CacheChatRoomMessagesUseCase {
    suspend operator fun invoke(chatRoomMessages: ChatRoomMessagesDomainModel)
}