package com.kreek.kreekandroid.data.datasource.cache

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.kreek.kreekandroid.common.util.PreferencesKeys
import com.kreek.kreekandroid.data.firebase.chat.model.ChatMessage
import com.kreek.kreekandroid.data.firebase.chat.model.ChatRoomInfo
import com.kreek.kreekandroid.data.firebase.chat.model.ChatRoomMessages
import com.kreek.kreekandroid.data.firebase.chat.model.toChatRoomMessage
import com.kreek.kreekandroid.data.firebase.doctor.model.Doctor
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class LocalCachedDataSourceImpl(context: Context) : LocalCachedDataSource {


    private val Context.dataStore by preferencesDataStore(name = PreferencesKeys.PREFERENCES_NAME)
    private val dataStore = context.dataStore
    private val gson = Gson()

    private val chatRoomMessagesFlow = MutableSharedFlow<List<ChatRoomMessages>>()

    override suspend fun cacheDoctor(doctor: Doctor) {
        val doctorJson = gson.toJson(doctor)
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.DOCTOR] = doctorJson
        }
    }

    override suspend fun getCacheDoctor(): Doctor? {
        val doctorJson = dataStore.data.map { preferences ->
            preferences[PreferencesKeys.DOCTOR]
        }.first()
        return if (doctorJson != null) gson.fromJson(doctorJson, Doctor::class.java) else null
    }

    // ChatRoomMessages
    override suspend fun cacheChatRoomInfoList(chatRoomInfoList: List<ChatRoomInfo>) {
        var chatRoomMessagesJson = dataStore.data.map { preferences ->
            preferences[PreferencesKeys.CHAT_ROOM_MESSAGES]
        }.first()
        val cachedChatRoomMessagesList = if (chatRoomMessagesJson != null) gson.fromJson(
            chatRoomMessagesJson,
            Array<ChatRoomMessages>::class.java
        ).toMutableList() else mutableListOf()
        for (chatRoomInfo in chatRoomInfoList) {
            val chatRoomMessage = chatRoomInfo.toChatRoomMessage()
            if (chatRoomMessage.chatRoomId.isNotBlank()) {
                val index =
                    cachedChatRoomMessagesList.indexOfFirst { it.chatRoomId == chatRoomMessage.chatRoomId }
                if (index != -1) {
                    // Update the existing ChatRoomMessages
                    cachedChatRoomMessagesList[index].chatType = chatRoomMessage.chatType
                    cachedChatRoomMessagesList[index].firstUserId = chatRoomMessage.firstUserId
                    cachedChatRoomMessagesList[index].secondUserId = chatRoomMessage.secondUserId
                    cachedChatRoomMessagesList[index].firstUserName = chatRoomMessage.firstUserName
                    cachedChatRoomMessagesList[index].secondUserName =
                        chatRoomMessage.secondUserName
                    cachedChatRoomMessagesList[index].firstUserSpeciality =
                        chatRoomMessage.firstUserSpeciality
                    cachedChatRoomMessagesList[index].secondUserSpeciality =
                        chatRoomMessage.secondUserSpeciality
                    cachedChatRoomMessagesList[index].patientId = chatRoomMessage.patientId
                    cachedChatRoomMessagesList[index].patientName = chatRoomMessage.patientName
                } else {
                    // Add the new ChatRoomMessages
                    cachedChatRoomMessagesList.add(chatRoomMessage)
                }
                // Cache the updated list
                chatRoomMessagesFlow.emit(cachedChatRoomMessagesList)
                chatRoomMessagesJson = gson.toJson(cachedChatRoomMessagesList)
                dataStore.edit { preferences ->
                    preferences[PreferencesKeys.CHAT_ROOM_MESSAGES] = chatRoomMessagesJson
                }
            }
        }
    }

    override suspend fun cacheChatRoomMessagesList(chatRoomMessagesList: List<ChatRoomMessages>) {
        var chatRoomMessagesJson = dataStore.data.map { preferences ->
            preferences[PreferencesKeys.CHAT_ROOM_MESSAGES]
        }.first()
        val cachedChatRoomMessagesList = if (chatRoomMessagesJson != null) gson.fromJson(
            chatRoomMessagesJson,
            Array<ChatRoomMessages>::class.java
        ).toMutableList() else mutableListOf()
        for (chatRoomMessage in chatRoomMessagesList) {
            if (chatRoomMessage.chatRoomId.isNotBlank()) {
                val index =
                    cachedChatRoomMessagesList.indexOfFirst { it.chatRoomId == chatRoomMessage.chatRoomId }
                if (index != -1) {
                    // Update the existing ChatRoomMessages
                    cachedChatRoomMessagesList[index] = chatRoomMessage
                } else {
                    // Add the new ChatRoomMessages
                    cachedChatRoomMessagesList.add(chatRoomMessage)
                }
                // Cache the updated list
                chatRoomMessagesFlow.emit(cachedChatRoomMessagesList)
                chatRoomMessagesJson = gson.toJson(cachedChatRoomMessagesList)
                dataStore.edit { preferences ->
                    preferences[PreferencesKeys.CHAT_ROOM_MESSAGES] = chatRoomMessagesJson
                }
            }
        }
    }

    override suspend fun cacheChatRoomMessages(chatRoomMessages: ChatRoomMessages) {
        if (chatRoomMessages.chatRoomId.isNotBlank()) {
            var chatRoomMessagesJson = dataStore.data.map { preferences ->
                preferences[PreferencesKeys.CHAT_ROOM_MESSAGES]
            }.first()
            val cachedChatRoomMessagesList = if (chatRoomMessagesJson != null) gson.fromJson(
                chatRoomMessagesJson,
                Array<ChatRoomMessages>::class.java
            ).toMutableList() else mutableListOf()
            val index =
                cachedChatRoomMessagesList.indexOfFirst { it.chatRoomId == chatRoomMessages.chatRoomId }
            if (index != -1) {
                // Update the existing ChatRoomMessages
                cachedChatRoomMessagesList[index] = chatRoomMessages
            } else {
                // Add the new ChatRoomMessages
                cachedChatRoomMessagesList.add(chatRoomMessages)
            }
            // Cache the updated list
            chatRoomMessagesFlow.emit(cachedChatRoomMessagesList)
            chatRoomMessagesJson = gson.toJson(cachedChatRoomMessagesList)
            dataStore.edit { preferences ->
                preferences[PreferencesKeys.CHAT_ROOM_MESSAGES] = chatRoomMessagesJson
            }
        }
    }

    override suspend fun updateChatRoomMessages(
        chatRoomId: String,
        lastMessage: String?,
        lastMessageTimestamp: Long?,
        numberOfUnreadMessages: Int?,
        chatMessageList: List<ChatMessage>?
    ): ChatRoomMessages {
        var chatRoomMessages = ChatRoomMessages()
        if (chatRoomId.isNotBlank()) {
            var chatRoomMessagesJson = dataStore.data.map { preferences ->
                preferences[PreferencesKeys.CHAT_ROOM_MESSAGES]
            }.first()
            val cachedChatRoomMessagesList = if (chatRoomMessagesJson != null) gson.fromJson(
                chatRoomMessagesJson,
                Array<ChatRoomMessages>::class.java
            ).toMutableList() else mutableListOf()
            val index =
                cachedChatRoomMessagesList.indexOfFirst { it.chatRoomId == chatRoomId }
            if (index != -1) {
                // Update the existing ChatRoomMessages
                if (lastMessage != null) {
                    cachedChatRoomMessagesList[index].lastMessage = lastMessage
                }
                if (lastMessageTimestamp != null) {
                    cachedChatRoomMessagesList[index].lastMessageTimestamp = lastMessageTimestamp
                }
                if (numberOfUnreadMessages != null) {
                    cachedChatRoomMessagesList[index].numberOfUnreadMessages =
                        numberOfUnreadMessages
                }
                if (chatMessageList != null) {
                    cachedChatRoomMessagesList[index].chatMessageList.addAll(chatMessageList)
                }
                chatRoomMessages = cachedChatRoomMessagesList[index]
            } else {
                // Add the new ChatRoomMessages
                chatRoomMessages = ChatRoomMessages(
                    chatRoomId = chatRoomId,
                    lastMessage = lastMessage,
                    lastMessageTimestamp = lastMessageTimestamp ?: 0,
                    numberOfUnreadMessages = numberOfUnreadMessages ?: 0,
                    chatMessageList = chatMessageList?.toMutableList() ?: mutableListOf(),
                    secondUserId = "",
                )
                cachedChatRoomMessagesList.add(
                    chatRoomMessages
                )
            }
            // Cache the updated list
            chatRoomMessagesFlow.emit(cachedChatRoomMessagesList)
            chatRoomMessagesJson = gson.toJson(cachedChatRoomMessagesList)
            dataStore.edit { preferences ->
                preferences[PreferencesKeys.CHAT_ROOM_MESSAGES] = chatRoomMessagesJson
            }
        }
        return chatRoomMessages
    }

    override suspend fun getChatRoomMessagesList(): List<ChatRoomMessages> {
        val chatRoomMessagesJson = dataStore.data.map { preferences ->
            preferences[PreferencesKeys.CHAT_ROOM_MESSAGES]
        }.first()
        return if (chatRoomMessagesJson != null) gson.fromJson(
            chatRoomMessagesJson,
            Array<ChatRoomMessages>::class.java
        ).toList() else emptyList()
    }

    override suspend fun getChatRoomMessagesListFlow(): MutableSharedFlow<List<ChatRoomMessages>> {
        val chatRoomMessagesJson = dataStore.data.map { preferences ->
            preferences[PreferencesKeys.CHAT_ROOM_MESSAGES]
        }.first()
        val cachedChatRoomMessagesList = if (chatRoomMessagesJson != null) gson.fromJson(
            chatRoomMessagesJson,
            Array<ChatRoomMessages>::class.java
        ).toList() else emptyList()
        chatRoomMessagesFlow.emit(cachedChatRoomMessagesList)
        return chatRoomMessagesFlow
    }

    override suspend fun getChatRoomMessages(chatRoomId: String): ChatRoomMessages? {
        val chatRoomMessagesJson = dataStore.data.map { preferences ->
            preferences[PreferencesKeys.CHAT_ROOM_MESSAGES]
        }.first()
        val cachedChatRoomMessagesList = if (chatRoomMessagesJson != null) gson.fromJson(
            chatRoomMessagesJson,
            Array<ChatRoomMessages>::class.java
        ).toList() else emptyList()
        return cachedChatRoomMessagesList.find { it.chatRoomId == chatRoomId }
    }
}