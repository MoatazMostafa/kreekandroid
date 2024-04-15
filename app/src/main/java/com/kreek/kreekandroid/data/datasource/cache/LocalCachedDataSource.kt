package com.kreek.kreekandroid.data.datasource.cache

import com.kreek.kreekandroid.data.firebase.chatmessage.model.ChatMessage
import com.kreek.kreekandroid.data.firebase.chatmessage.model.ChatRoomInfo
import com.kreek.kreekandroid.data.firebase.doctor.model.Doctor

interface LocalCachedDataSource {
    suspend fun cacheLastMessageTimestamp(timestamp: Long)
    suspend fun getLastMessageTimestamp(): Long
    suspend fun cacheDoctor(doctor: Doctor)
    suspend fun getCacheDoctor(): Doctor?
    suspend fun cacheChatMessages(messages: List<ChatMessage>)
    suspend fun getCachedChatMessages(): List<ChatMessage>
    suspend fun cacheChatRoomInfoList(chatRoomInfoList: List<ChatRoomInfo>)
    suspend fun getCachedChatRoomInfoList(): List<ChatRoomInfo>
    suspend fun updateCachedChatRoomInfo(chatRoomInfo: ChatRoomInfo)
}