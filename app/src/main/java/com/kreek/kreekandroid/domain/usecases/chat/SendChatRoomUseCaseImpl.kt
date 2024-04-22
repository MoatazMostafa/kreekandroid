package com.kreek.kreekandroid.domain.usecases.chat

import com.kreek.kreekandroid.data.repository.FirebaseRepository
import com.kreek.kreekandroid.domain.model.ChatRoomInfoDomainModel
import com.kreek.kreekandroid.domain.model.toDataModel

class SendChatRoomUseCaseImpl(
    private val firebaseRepository: FirebaseRepository
) : SendChatRoomUseCase {
    override suspend fun invoke(chatRoomInfo: ChatRoomInfoDomainModel) {
        firebaseRepository.sendChatRoom(chatRoomInfo.toDataModel())
    }
}