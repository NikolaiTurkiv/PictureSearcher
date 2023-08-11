package com.test.repository_search.domain

import kotlinx.coroutines.flow.Flow

interface SearchRemoteRepository {
    suspend fun searchPhotos(searchRequest: String): Result<List<DownloadedImages>>
}