package com.test.repository_search.data

import com.test.core_db.dao.RequestDao
import com.test.core_db.entities.RequestInfoEntity
import com.test.repository_search.domain.SearchLocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class SearchLocalRepositoryImpl(
    private val db: RequestDao
) : SearchLocalRepository {

    override suspend fun getSavedRequests(requestPart: String): Flow<Result<List<String>>> {
        return flow {
            emit(Result.success(db.getRequestList(requestPart).map { it.requestPart }))
        }.catch {
            emit(Result.failure(it))
        }
    }

    override suspend fun saveRequestPart(requestPart: String) {
        db.insert(RequestInfoEntity(requestPart))
    }
}