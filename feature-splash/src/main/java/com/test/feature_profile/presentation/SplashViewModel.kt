package com.test.feature_profile.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
   private val profileNavigator: SplashNavigator
): ViewModel() {

    fun goToSearchScreen(){
        profileNavigator.navigateToSearchScreen()
    }
}
