package com.kreek.kreekandroid.common.util

import androidx.datastore.preferences.core.stringPreferencesKey

object PreferencesKeys {
    const val PREFERENCES_NAME = "KreekPreferences"

    val DOCTOR = stringPreferencesKey("doctor")
    //TODO: Patient
    val CHAT_ROOM_MESSAGES = stringPreferencesKey("chat_room_messages")
}