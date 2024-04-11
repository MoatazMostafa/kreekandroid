package com.kreek.kreekandroid.domain.usecases.lastmessagetimestamp

interface GetCachedLastMessageTimestampUseCase {

    suspend fun getLastMessageTimestamp(): Long
}