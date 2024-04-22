package com.kreek.kreekandroid.domain.usecases.chat.local

import com.kreek.kreekandroid.domain.model.ChatRoomMessagesDomainModel

interface GetCachedChatRoomMessagesUseCase {
    suspend operator fun invoke(chatRoomId: String): ChatRoomMessagesDomainModel?
}