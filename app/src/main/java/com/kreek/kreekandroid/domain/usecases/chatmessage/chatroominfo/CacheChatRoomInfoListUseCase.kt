package com.kreek.kreekandroid.domain.usecases.chatmessage.chatroominfo

import com.kreek.kreekandroid.domain.model.ChatRoomInfoDomainModel

interface CacheChatRoomInfoListUseCase {
    suspend operator fun invoke(chatRoomInfoDomainModelList: List<ChatRoomInfoDomainModel>)
}