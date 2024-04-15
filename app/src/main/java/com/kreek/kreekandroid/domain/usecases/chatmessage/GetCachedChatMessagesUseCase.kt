package com.kreek.kreekandroid.domain.usecases.chatmessage

import com.kreek.kreekandroid.domain.model.ChatMessageDomainModel

interface GetCachedChatMessagesUseCase {
    suspend operator fun invoke(): List<ChatMessageDomainModel>
}