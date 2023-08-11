package com.test.repository_search.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext


class SearchUseCase(
    private val remoteRepository: SearchRemoteRepository,
    private val localRepository: SearchLocalRepository
) {
    suspend operator fun invoke(searchRequest: String): Result<List<DownloadedImages>> =
        withContext(
            Dispatchers.IO
        ) {
            remoteRepository.searchPhotos(searchRequest).onSuccess {
                return@withContext Result.success(it)
            }.onFailure {
                return@withContext Result.failure(it)
            }
        }

    suspend fun getRequestParts(part: String): Flow<Result<List<String>>> {
        return localRepository.getSavedRequests(part)
    }

    suspend fun saveRequestPart(part: String) {
        localRepository.saveRequestPart(part)
    }
}
