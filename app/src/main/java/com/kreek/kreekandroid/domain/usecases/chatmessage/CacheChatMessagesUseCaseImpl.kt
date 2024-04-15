package com.kreek.kreekandroid.domain.usecases.chatmessage

import com.kreek.kreekandroid.data.repository.LocalCachedRepository
import com.kreek.kreekandroid.domain.model.ChatMessageDomainModel
import com.kreek.kreekandroid.domain.model.toDataModel

class CacheChatMessagesUseCaseImpl(
    private val localCachedRepository: LocalCachedRepository
):CacheChatMessagesUseCase {
    override suspend fun invoke(messages: List<ChatMessageDomainModel>) {
        localCachedRepository.cacheChatMessages(messages.map { it.toDataModel() })
    }
}