package com.test.repository_search

import com.test.core_db.dao.RequestDao
import com.test.core_network.data.NetworkApi
import com.test.repository_search.data.SearchLocalRepositoryImpl
import com.test.repository_search.data.SearchRemoteRepositoryImpl
import com.test.repository_search.domain.SearchLocalRepository
import com.test.repository_search.domain.SearchRemoteRepository
import com.test.repository_search.domain.SearchUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SearchRepositoryModule {

    @Provides
    @Singleton
    fun provideSearchRemoteRepository(api: NetworkApi): SearchRemoteRepository =
        SearchRemoteRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideSearchLocalRepositoryPhotos(db: RequestDao): SearchLocalRepository =
        SearchLocalRepositoryImpl(db)

    @Provides
    @Singleton
    fun provideSearchPhotosUseCase(
        searchRemoteRepository: SearchRemoteRepository,
        searchLocalRepository: SearchLocalRepository
    ) =
        SearchUseCase(searchRemoteRepository, searchLocalRepository)
}