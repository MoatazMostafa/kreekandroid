package com.kreek.kreekandroid.domain.usecases.chat.local

import com.kreek.kreekandroid.domain.model.ChatRoomInfoDomainModel

interface CacheChatRoomInfoListUseCase {
    suspend operator fun invoke(chatRoomInfoList: List<ChatRoomInfoDomainModel>)
}