package com.kreek.kreekandroid.domain.usecases.chat.local

import com.kreek.kreekandroid.domain.model.ChatRoomMessagesDomainModel

interface GetCachedChatRoomMessagesListUseCase {
    suspend operator fun invoke(): List<ChatRoomMessagesDomainModel>
}