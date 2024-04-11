package com.kreek.kreekandroid.data.repository

interface LocalCachedRepository {
    suspend fun cacheLastMessageTimestamp(timestamp: Long)
    suspend fun getLastMessageTimestamp(): Long
}