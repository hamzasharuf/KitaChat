package com.hamzasharuf.kitachat.ui.splash

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.hamzasharuf.kitachat.R
import com.hamzasharuf.kitachat.databinding.FragmentSplashBinding
import com.hamzasharuf.kitachat.ui.MainSharedViewModel
import com.hamzasharuf.kitachat.ui.base.BaseFragment
import com.hamzasharuf.kitachat.utils.common.AppConstants.SPLASH_SCREEN_DELAY_TIME
import com.hamzasharuf.kitachat.utils.extensions.isNotNull
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class SplashFragment : BaseFragment<SplashViewModel, FragmentSplashBinding>() {

    private val viewModel: SplashViewModel by viewModels()
    private val sharedViewModel: MainSharedViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigateToHome()
    }


    private fun navigateToHome() {
        lifecycleScope.launch(Main) {

            delay(SPLASH_SCREEN_DELAY_TIME)

            val cachedUser = sharedViewModel.cachedUser
            val firebaseUser = sharedViewModel.firebaseUser


            if (cachedUser.isNotNull() && firebaseUser.isNotNull())
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
            else
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToWelcomeFragment())

        }
    }

    override fun getLayoutRes(): Int = R.layout.fragment_splash
}