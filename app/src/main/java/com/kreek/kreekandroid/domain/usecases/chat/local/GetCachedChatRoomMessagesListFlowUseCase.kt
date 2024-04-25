package com.kreek.kreekandroid.domain.usecases.chat.local

import com.kreek.kreekandroid.data.firebase.chat.model.ChatRoomMessages
import kotlinx.coroutines.flow.MutableSharedFlow

interface GetCachedChatRoomMessagesListFlowUseCase {
    suspend operator fun invoke(): MutableSharedFlow<List<ChatRoomMessages>>
}