package com.kreek.kreekandroid.domain.usecases.chatmessage

import com.kreek.kreekandroid.domain.model.ChatMessageDomainModel

interface SendChatMessageUseCase {
    suspend fun sendChatMessage(message: ChatMessageDomainModel)
}