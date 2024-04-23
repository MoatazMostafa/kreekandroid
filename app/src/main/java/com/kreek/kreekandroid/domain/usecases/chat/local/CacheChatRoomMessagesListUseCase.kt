package com.kreek.kreekandroid.domain.usecases.chat.local

import com.kreek.kreekandroid.domain.model.ChatRoomMessagesDomainModel

interface CacheChatRoomMessagesListUseCase {
    suspend operator fun invoke(chatRoomMessagesList: List<ChatRoomMessagesDomainModel>)
}