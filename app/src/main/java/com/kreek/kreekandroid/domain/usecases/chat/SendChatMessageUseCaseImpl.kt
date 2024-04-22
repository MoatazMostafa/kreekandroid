package com.kreek.kreekandroid.domain.usecases.chat

import com.kreek.kreekandroid.data.repository.FirebaseRepository
import com.kreek.kreekandroid.domain.model.ChatMessageDomainModel
import com.kreek.kreekandroid.domain.model.toDataModel

class SendChatMessageUseCaseImpl(
    private val firebaseChatRepository: FirebaseRepository
): SendChatMessageUseCase{
    override suspend fun sendChatMessage(message: ChatMessageDomainModel) {
        firebaseChatRepository.sendChatMessage(message.toDataModel())
    }
}