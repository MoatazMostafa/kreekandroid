package com.kreek.kreekandroid.data.datasource.cache

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.kreek.kreekandroid.common.util.PreferencesKeys
import com.kreek.kreekandroid.data.firebase.chat.model.ChatRoomMessages
import com.kreek.kreekandroid.data.firebase.doctor.model.Doctor
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class LocalCachedDataSourceImpl(context: Context) : LocalCachedDataSource {

    private val Context.dataStore by preferencesDataStore(name = PreferencesKeys.PREFERENCES_NAME)
    private val dataStore = context.dataStore
    private val gson = Gson()

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
    override suspend fun cacheChatRoomMessagesList(chatRoomMessages: List<ChatRoomMessages>) {
        val cachedChatRoomMessagesList = getChatRoomMessagesList().toMutableList()
        for (chatRoomMessage in chatRoomMessages) {
            if (chatRoomMessage.chatRoomId.isNotBlank()) {
                val index =
                    cachedChatRoomMessagesList.indexOfFirst { it.chatRoomId == chatRoomMessage.chatRoomId }
                if (index != -1) {
                    // Update the existing ChatRoomMessages
                    cachedChatRoomMessagesList[index] = chatRoomMessage
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
                val chatRoomMessagesJson = gson.toJson(cachedChatRoomMessagesList)
                dataStore.edit { preferences ->
                    preferences[PreferencesKeys.CHAT_ROOM_MESSAGES] = chatRoomMessagesJson
                }
            }
        }
    }

    override suspend fun cacheChatRoomMessages(chatRoomMessages: ChatRoomMessages) {
        val cachedChatRoomMessagesList = getChatRoomMessagesList().toMutableList()
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
        cacheChatRoomMessagesList(cachedChatRoomMessagesList)
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

    override suspend fun getChatRoomMessages(chatRoomId: String): ChatRoomMessages? {
        val cachedChatRoomMessagesList = getChatRoomMessagesList()
        return cachedChatRoomMessagesList.find { it.chatRoomId == chatRoomId }
    }
}