package com.test.repository_search.domain

import kotlinx.coroutines.flow.Flow

interface SearchLocalRepository {
    suspend fun getSavedRequests(requestPart: String): Flow<Result<List<String>>>
    suspend fun saveRequestPart(requestPart: String)
}