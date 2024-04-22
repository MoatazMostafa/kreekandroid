package com.kreek.kreekandroid.data.repository

import com.kreek.kreekandroid.data.firebase.chat.model.ChatRoomMessages
import com.kreek.kreekandroid.data.firebase.doctor.model.Doctor
import com.kreek.kreekandroid.domain.model.ChatMessageDomainModel
import com.kreek.kreekandroid.domain.model.ChatRoomInfoDomainModel
import com.kreek.kreekandroid.domain.model.ChatRoomMessagesDomainModel

interface LocalCachedRepository {
    suspend fun cacheDoctor(doctor: Doctor)
    suspend fun getCacheDoctor(): Doctor?

    ////
    suspend fun cacheChatRoomMessagesList(chatRoomInfo: List<ChatRoomInfoDomainModel>)
    suspend fun cacheChatRoomMessages(chatRoomMessages: ChatRoomMessages)
    suspend fun getCachedChatRoomMessagesList(): List<ChatRoomMessages>
    suspend fun getCachedChatRoomMessages(chatRoomId: String): ChatRoomMessagesDomainModel?
    suspend fun updateCachedChatRoomMessages(
        chatRoomId: String,
        lastMessage: String?,
        lastMessageTimestamp: Long?,
        numberOfUnreadMessages: Int?,
        chatMessageList: List<ChatMessageDomainModel>?
    ): ChatRoomMessagesDomainModel
}