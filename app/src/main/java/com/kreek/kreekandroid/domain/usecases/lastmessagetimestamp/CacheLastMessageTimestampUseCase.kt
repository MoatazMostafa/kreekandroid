package com.kreek.kreekandroid.domain.usecases.lastmessagetimestamp

interface CacheLastMessageTimestampUseCase {
    suspend fun saveLastMessageTimestamp(timestamp: Long)
}