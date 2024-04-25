package com.kreek.kreekandroid.domain.usecases.chat.local

import com.kreek.kreekandroid.data.firebase.chat.model.ChatRoomMessages
import com.kreek.kreekandroid.data.repository.LocalCachedRepository
import com.kreek.kreekandroid.domain.model.ChatRoomMessagesDomainModel
import com.kreek.kreekandroid.domain.model.toDomainModel
import kotlinx.coroutines.flow.MutableSharedFlow

class GetCachedChatRoomMessagesListFlowUseCaseImpl(
    private val localCachedRepository: LocalCachedRepository
): GetCachedChatRoomMessagesListFlowUseCase{
    override suspend operator fun invoke(): MutableSharedFlow<List<ChatRoomMessages>> {
        return localCachedRepository.getCachedChatRoomMessagesListFlow()
    }
}