package com.kreek.kreekandroid.domain.usecases.lastmessagetimestamp

import com.kreek.kreekandroid.data.repository.LocalCachedRepository

class GetCachedLastMessageTimestampUseCaseImpl(
    private val localCachedRepository: LocalCachedRepository
) : GetCachedLastMessageTimestampUseCase {
    override suspend fun getLastMessageTimestamp(): Long {
        return localCachedRepository.getLastMessageTimestamp()
    }
}