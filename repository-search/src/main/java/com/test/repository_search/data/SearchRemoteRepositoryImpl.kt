package com.test.repository_search.data

import com.test.core_network.data.NetworkApi
import com.test.core_network.toResult
import com.test.repository_search.domain.DownloadedImages
import com.test.repository_search.domain.SearchRemoteRepository
import com.test.repository_search.utils.toDownloadedImages
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchRemoteRepositoryImpl(
    private val api: NetworkApi
) : SearchRemoteRepository {
    override suspend fun searchPhotos(searchRequest: String): Result<List<DownloadedImages>> =
        withContext(
            Dispatchers.IO
        ) {
            try {
                val searchResponse = api.searchPictures(query = searchRequest)

                return@withContext searchResponse.toResult().map { it.toDownloadedImages() }

            } catch (e: Exception) {
                return@withContext Result.failure(e)
            }
        }
}
