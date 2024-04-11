package com.kreek.kreekandroid.domain.usecases.lastmessagetimestamp

import com.kreek.kreekandroid.data.repository.LocalCachedRepository

class CacheLastMessageTimestampUseCaseImpl(
    private val localCachedRepository: LocalCachedRepository
):CacheLastMessageTimestampUseCase {
    override suspend fun saveLastMessageTimestamp(timestamp: Long) {
        localCachedRepository.cacheLastMessageTimestamp(timestamp)
    }
}