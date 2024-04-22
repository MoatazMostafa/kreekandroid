package com.kreek.kreekandroid.domain.usecases.chat.local

import com.kreek.kreekandroid.domain.model.ChatMessageDomainModel
import com.kreek.kreekandroid.domain.model.ChatRoomMessagesDomainModel

interface UpdateCachedChatRoomMessages {
    suspend operator fun invoke(
        chatRoomId: String,
        lastMessage: String? = null,
        lastMessageTimestamp: Long? = null,
        numberOfUnreadMessages: Int? = null,
        chatMessageList: List<ChatMessageDomainModel>? = null
    ): ChatRoomMessagesDomainModel
}