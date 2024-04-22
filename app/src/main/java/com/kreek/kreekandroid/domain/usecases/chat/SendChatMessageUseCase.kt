package com.kreek.kreekandroid.domain.usecases.chat

import com.kreek.kreekandroid.domain.model.ChatMessageDomainModel

interface SendChatMessageUseCase {
    suspend fun sendChatMessage(message: ChatMessageDomainModel)
}