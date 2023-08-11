package com.test.test_app.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.test.lib_android_utils.navigation.NavControllerHolder
import com.test.test_app.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

     @Inject
    lateinit var navControllerHolders: Array<NavControllerHolder>

    private val navController: NavController by lazy {
        navHostFragment.findNavController()
    }

    private val navHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.nav_fragment) as NavHostFragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        val decor: View = window.decorView
        decor.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.statusBarColor = this.resources.getColor(R.color.white);

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setNavControllerToNavigators()
    }

    private fun setNavControllerToNavigators() {
        navControllerHolders.forEach { it.navController = navController }
    }

}
