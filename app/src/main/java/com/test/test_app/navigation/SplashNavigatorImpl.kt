package com.test.test_app.navigation

import androidx.navigation.NavController
import com.test.feature_profile.presentation.SplashNavigator
import com.test.test_app.R

class SplashNavigatorImpl: SplashNavigator {

    override fun navigateToSearchScreen() {
        navController?.navigate(R.id.action_splashFragment_to_searchFragment)
    }

    override var navController: NavController? = null
}