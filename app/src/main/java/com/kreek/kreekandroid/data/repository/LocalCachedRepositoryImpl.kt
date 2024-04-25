package com.kreek.kreekandroid.data.repository

import com.kreek.kreekandroid.data.datasource.cache.LocalCachedDataSource
import com.kreek.kreekandroid.data.firebase.chat.model.ChatRoomMessages
import com.kreek.kreekandroid.data.firebase.doctor.model.Doctor
import com.kreek.kreekandroid.domain.model.ChatMessageDomainModel
import com.kreek.kreekandroid.domain.model.ChatRoomInfoDomainModel
import com.kreek.kreekandroid.domain.model.ChatRoomMessagesDomainModel
import com.kreek.kreekandroid.domain.model.toDataModel
import com.kreek.kreekandroid.domain.model.toDomainModel
import kotlinx.coroutines.flow.MutableSharedFlow

class LocalCachedRepositoryImpl(
    private val localCachedDataSource: LocalCachedDataSource
) : LocalCachedRepository {
    override suspend fun cacheDoctor(doctor: Doctor) {
        localCachedDataSource.cacheDoctor(doctor)
    }

    override suspend fun getCacheDoctor(): Doctor? {
        return localCachedDataSource.getCacheDoctor()
    }

    override suspend fun cacheChatRoomInfoList(chatRoomInfo: List<ChatRoomInfoDomainModel>) {
        localCachedDataSource.cacheChatRoomInfoList(chatRoomInfo.map { it.toDataModel() })
    }

    override suspend fun cacheChatRoomMessagesList(chatRoomMessagesList: List<ChatRoomMessagesDomainModel>) {
        localCachedDataSource.cacheChatRoomMessagesList(chatRoomMessagesList.map { it.toDataModel() })
    }

    override suspend fun getCachedChatRoomMessagesList(): List<ChatRoomMessages> {
        return localCachedDataSource.getChatRoomMessagesList()
    }

    override suspend fun getCachedChatRoomMessagesListFlow(): MutableSharedFlow<List<ChatRoomMessages>> {
        return localCachedDataSource.getChatRoomMessagesListFlow()
    }

    override suspend fun getCachedChatRoomMessages(chatRoomId: String): ChatRoomMessagesDomainModel? {
        return localCachedDataSource.getChatRoomMessages(chatRoomId)?.toDomainModel()
    }

    override suspend fun updateCachedChatRoomMessages(
        chatRoomId: String,
        lastMessage: String?,
        lastMessageTimestamp: Long?,
        numberOfUnreadMessages: Int?,
        chatMessageList: List<ChatMessageDomainModel>?
    ): ChatRoomMessagesDomainModel {
        return localCachedDataSource.updateChatRoomMessages(
            chatRoomId,
            lastMessage,
            lastMessageTimestamp,
            numberOfUnreadMessages,
           chatMessageList?.map { it.toDataModel() }
        ).toDomainModel()
    }
}