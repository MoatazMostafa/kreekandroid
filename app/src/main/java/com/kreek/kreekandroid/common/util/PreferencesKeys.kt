package com.kreek.kreekandroid.common.util

import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferencesKeys {
    const val PREFERENCES_NAME = "KreekPreferences"

    val LAST_MESSAGE_TIMESTAMP = longPreferencesKey("last_message_timestamp")
    val DOCTOR = stringPreferencesKey("doctor")
}