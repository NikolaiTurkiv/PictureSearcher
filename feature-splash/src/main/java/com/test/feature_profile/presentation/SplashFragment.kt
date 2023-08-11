package com.test.feature_profile.presentation

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.test.android_utils.viewBinding
import com.test.feature_profile.R
import com.test.feature_profile.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.UUID


@AndroidEntryPoint
class SplashFragment : Fragment(R.layout.fragment_splash) {

    val binding by viewBinding<FragmentSplashBinding>()
    private val viewModel by viewModels<SplashViewModel>()

    private var progress = 0

    private var countDownTimer: CountDownTimer? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startTimer()
    }

    private fun startTimer() {
        countDownTimer = object : CountDownTimer(3000, 30) {

            override fun onTick(p0: Long) {
                progress += 1
                binding.progressBar.progress = progress
            }

            override fun onFinish() {
                viewModel.goToSearchScreen()
            }

        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer?.cancel()
    }
}
