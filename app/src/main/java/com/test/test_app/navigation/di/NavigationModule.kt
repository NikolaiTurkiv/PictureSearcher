package com.test.test_app.navigation.di

import com.test.lib_android_utils.navigation.NavControllerHolder
import com.test.feature_search.presentation.SearchNavigator
import com.test.feature_profile.presentation.SplashNavigator
import com.test.test_app.navigation.SplashNavigatorImpl
import com.test.test_app.navigation.SearchNavigatorImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NavigationModule {

    @Provides
    @Singleton
    fun provideSearchNavigator(): SearchNavigator = SearchNavigatorImpl()

    @Provides
    @Singleton
    fun provideSplashNavigator(): SplashNavigator = SplashNavigatorImpl()


    @Provides
    @Singleton
    fun provideNavControllerHolders(
        searchNavigator: SearchNavigator,
        profileNavigator: SplashNavigator,
    ):Array<NavControllerHolder> =
        arrayOf(
            searchNavigator,
            profileNavigator,
         )
}