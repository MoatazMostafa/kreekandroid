package com.kreek.kreekandroid.data.repository

import com.kreek.kreekandroid.data.datasource.cache.LocalCachedDataSource
import com.kreek.kreekandroid.data.firebase.chatmessage.model.ChatMessage
import com.kreek.kreekandroid.data.firebase.chatmessage.model.ChatRoomInfo
import com.kreek.kreekandroid.data.firebase.doctor.model.Doctor

class LocalCachedRepositoryImpl(
    private val localCachedDataSource: LocalCachedDataSource
) : LocalCachedRepository {
    override suspend fun cacheLastMessageTimestamp(timestamp: Long) {
        localCachedDataSource.cacheLastMessageTimestamp(timestamp)
    }

    override suspend fun getLastMessageTimestamp(): Long {
        return localCachedDataSource.getLastMessageTimestamp()
    }

    override suspend fun cacheDoctor(doctor: Doctor) {
        localCachedDataSource.cacheDoctor(doctor)
    }

    override suspend fun getCacheDoctor(): Doctor? {
        return localCachedDataSource.getCacheDoctor()
    }

    override suspend fun cacheChatMessages(messages: List<ChatMessage>) {
        localCachedDataSource.cacheChatMessages(messages)
    }

    override suspend fun getCachedChatMessages(): List<ChatMessage> {
        return localCachedDataSource.getCachedChatMessages()
    }

    override suspend fun cacheChatRoomInfoList(chatRoomInfoList: List<ChatRoomInfo>) {
        localCachedDataSource.cacheChatRoomInfoList(chatRoomInfoList)
    }

    override suspend fun getCachedChatRoomInfoList(): List<ChatRoomInfo> {
        return localCachedDataSource.getCachedChatRoomInfoList()
    }

    override suspend fun updateCachedChatRoomInfo(chatRoomInfo: ChatRoomInfo) {
        localCachedDataSource.updateCachedChatRoomInfo(chatRoomInfo)
    }
}