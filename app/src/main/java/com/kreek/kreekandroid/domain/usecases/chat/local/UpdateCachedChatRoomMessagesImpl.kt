package com.kreek.kreekandroid.domain.usecases.chat.local

import com.kreek.kreekandroid.data.repository.LocalCachedRepository
import com.kreek.kreekandroid.domain.model.ChatMessageDomainModel
import com.kreek.kreekandroid.domain.model.ChatRoomMessagesDomainModel

class UpdateCachedChatRoomMessagesImpl(
    private val localCachedRepository: LocalCachedRepository
):UpdateCachedChatRoomMessages {
    override suspend fun invoke(
        chatRoomId: String,
        lastMessage: String?,
        lastMessageTimestamp: Long?,
        numberOfUnreadMessages: Int?,
        chatMessageList: List<ChatMessageDomainModel>?
    ): ChatRoomMessagesDomainModel {
       return localCachedRepository.updateCachedChatRoomMessages(
            chatRoomId,
            lastMessage,
            lastMessageTimestamp,
            numberOfUnreadMessages,
            chatMessageList
        )
    }
}