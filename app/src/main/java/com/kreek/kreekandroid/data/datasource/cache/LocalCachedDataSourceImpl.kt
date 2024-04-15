package com.kreek.kreekandroid.data.datasource.cache

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.kreek.kreekandroid.common.util.PreferencesKeys
import com.kreek.kreekandroid.data.firebase.chatmessage.model.ChatMessage
import com.kreek.kreekandroid.data.firebase.chatmessage.model.ChatRoomInfo
import com.kreek.kreekandroid.data.firebase.doctor.model.Doctor
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class LocalCachedDataSourceImpl(context: Context) : LocalCachedDataSource {

    private val Context.dataStore by preferencesDataStore(name = PreferencesKeys.PREFERENCES_NAME)
    private val dataStore = context.dataStore
    private val gson = Gson()


    override suspend fun cacheLastMessageTimestamp(timestamp: Long) {
        dataStore.edit { preferences ->
            if (timestamp > (preferences[PreferencesKeys.LAST_MESSAGE_TIMESTAMP] ?: 0)) {
                preferences[PreferencesKeys.LAST_MESSAGE_TIMESTAMP] = timestamp
            }
        }
    }

    override suspend fun getLastMessageTimestamp(): Long {
        return dataStore.data.map { preferences ->
            preferences[PreferencesKeys.LAST_MESSAGE_TIMESTAMP] ?: 0
        }.first()
    }

    override suspend fun cacheChatMessages(messages: List<ChatMessage>) {
        val messagesJson = gson.toJson(messages)
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.CHAT_MESSAGES] = messagesJson
        }
    }

    override suspend fun getCachedChatMessages(): List<ChatMessage> {
        val messagesJson = dataStore.data.map { preferences ->
            preferences[PreferencesKeys.CHAT_MESSAGES]
        }.first()
        return if (messagesJson != null) gson.fromJson(messagesJson, Array<ChatMessage>::class.java)
            .toList() else emptyList()
    }

    override suspend fun cacheChatRoomInfoList(chatRoomInfoList: List<ChatRoomInfo>) {
        val chatRoomInfoJson = gson.toJson(chatRoomInfoList)
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.CHAT_ROOM_INFO_LIST] = chatRoomInfoJson
        }
    }

    override suspend fun getCachedChatRoomInfoList(): List<ChatRoomInfo> {
        val chatRoomInfoJson = dataStore.data.map { preferences ->
            preferences[PreferencesKeys.CHAT_ROOM_INFO_LIST]
        }.first()
        return if (chatRoomInfoJson != null) gson.fromJson(
            chatRoomInfoJson,
            Array<ChatRoomInfo>::class.java
        )
            .toList() else emptyList()
    }

    override suspend fun updateCachedChatRoomInfo(chatRoomInfo: ChatRoomInfo) {
        val chatRoomInfoList = getCachedChatRoomInfoList().toMutableList()
        val index = chatRoomInfoList.indexOfFirst { it.chatRoomId == chatRoomInfo.chatRoomId }
        if (index != -1) {
            chatRoomInfoList[index] = chatRoomInfo
            cacheChatRoomInfoList(chatRoomInfoList)
        }
    }

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
}