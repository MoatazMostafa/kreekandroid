package com.kreek.kreekandroid.data.repository

import com.kreek.kreekandroid.data.datasource.cache.LocalCachedDataSource

class LocalCachedRepositoryImpl(
    private val localCachedDataSource: LocalCachedDataSource
) : LocalCachedRepository {
    override suspend fun cacheLastMessageTimestamp(timestamp: Long) {
        localCachedDataSource.cacheLastMessageTimestamp(timestamp)
    }

    override suspend fun getLastMessageTimestamp(): Long {
        return localCachedDataSource.getLastMessageTimestamp()
    }
}