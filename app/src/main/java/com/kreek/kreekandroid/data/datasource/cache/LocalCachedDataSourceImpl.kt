package com.kreek.kreekandroid.data.datasource.cache

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.kreek.kreekandroid.common.util.PreferencesKeys
import com.kreek.kreekandroid.data.firebase.doctor.model.Doctor
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class LocalCachedDataSourceImpl(context: Context) : LocalCachedDataSource {

    private val Context.dataStore by preferencesDataStore(name = PreferencesKeys.PREFERENCES_NAME)
    private val dataStore = context.dataStore
    private val gson = Gson()


    override suspend fun cacheLastMessageTimestamp(timestamp: Long) {
        dataStore.edit { preferences ->
            if(timestamp > (preferences[PreferencesKeys.LAST_MESSAGE_TIMESTAMP] ?: 0)) {
                preferences[PreferencesKeys.LAST_MESSAGE_TIMESTAMP] = timestamp
            }
        }
    }

    override suspend fun getLastMessageTimestamp(): Long {
        return dataStore.data.map { preferences ->
            preferences[PreferencesKeys.LAST_MESSAGE_TIMESTAMP] ?: 0
        }.first()
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