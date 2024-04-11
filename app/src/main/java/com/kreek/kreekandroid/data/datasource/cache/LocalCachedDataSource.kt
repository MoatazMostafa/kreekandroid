package com.kreek.kreekandroid.data.datasource.cache

import com.kreek.kreekandroid.data.firebase.doctor.model.Doctor

interface LocalCachedDataSource {
    suspend fun cacheLastMessageTimestamp(timestamp: Long)
    suspend fun getLastMessageTimestamp(): Long
    suspend fun cacheDoctor(doctor: Doctor)
    suspend fun getCacheDoctor(): Doctor?
}