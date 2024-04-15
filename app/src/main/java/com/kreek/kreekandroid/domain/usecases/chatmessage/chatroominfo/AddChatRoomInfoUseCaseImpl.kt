package com.kreek.kreekandroid.domain.usecases.chatmessage.chatroominfo

import com.kreek.kreekandroid.data.repository.FirebaseRepository
import com.kreek.kreekandroid.domain.model.ChatRoomInfoDomainModel
import com.kreek.kreekandroid.domain.model.toDataModel

class AddChatRoomInfoUseCaseImpl(
    private val firebaseRepository: FirebaseRepository
) : AddChatRoomInfoUseCase {
    override fun invoke(chatRoomInfo: ChatRoomInfoDomainModel) {
        firebaseRepository.addChatRoomInfo(chatRoomInfo.toDataModel())
    }
}