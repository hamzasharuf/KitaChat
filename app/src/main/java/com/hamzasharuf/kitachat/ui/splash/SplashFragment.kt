package com.hamzasharuf.kitachat.ui.splash

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.hamzasharuf.kitachat.R
import com.hamzasharuf.kitachat.databinding.FragmentSplashBinding
import com.hamzasharuf.kitachat.ui.base.BaseFragment
import com.hamzasharuf.kitachat.utils.common.AppConstants.SPLASH_SCREEN_DELAY_TIME
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class SplashFragment : BaseFragment<SplashViewModel, FragmentSplashBinding>() {

    private val viewModel: SplashViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigateToHome()
    }

    private fun navigateToHome() {
        lifecycleScope.launch(Main) {
            delay(SPLASH_SCREEN_DELAY_TIME)
            if (viewModel.isRegistered)
                findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
            else
                findNavController().navigate(R.id.action_splashFragment_to_welcomeFragment)
        }
    }

    override fun getLayoutRes(): Int = R.layout.fragment_splash
}