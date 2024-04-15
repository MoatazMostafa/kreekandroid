package com.kreek.kreekandroid.domain.usecases.chatmessage

import com.kreek.kreekandroid.data.repository.LocalCachedRepository
import com.kreek.kreekandroid.domain.model.ChatMessageDomainModel
import com.kreek.kreekandroid.domain.model.toDomainModel

class GetCachedChatMessagesUseCaseImpl(
    private val localCachedRepository: LocalCachedRepository
): GetCachedChatMessagesUseCase{
    override suspend fun invoke(): List<ChatMessageDomainModel> {
        return localCachedRepository.getCachedChatMessages().map { it.toDomainModel() }
    }
}