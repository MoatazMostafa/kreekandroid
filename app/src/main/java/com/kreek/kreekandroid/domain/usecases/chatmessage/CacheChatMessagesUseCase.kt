package com.kreek.kreekandroid.domain.usecases.chatmessage

import com.kreek.kreekandroid.domain.model.ChatMessageDomainModel

interface CacheChatMessagesUseCase {
    suspend operator fun invoke(messages: List<ChatMessageDomainModel>)
}