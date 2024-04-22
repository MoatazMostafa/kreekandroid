package com.kreek.kreekandroid.data.datasource.cache

import com.kreek.kreekandroid.data.firebase.chat.model.ChatRoomMessages
import com.kreek.kreekandroid.data.firebase.doctor.model.Doctor

interface LocalCachedDataSource {
    suspend fun cacheDoctor(doctor: Doctor)
    suspend fun getCacheDoctor(): Doctor?

    // ChatRoomMessages
    suspend fun cacheChatRoomMessagesList(chatRoomMessages: List<ChatRoomMessages>)
    suspend fun cacheChatRoomMessages(chatRoomMessages: ChatRoomMessages)
    suspend fun getChatRoomMessagesList(): List<ChatRoomMessages>
    suspend fun getChatRoomMessages(chatRoomId: String): ChatRoomMessages?
}