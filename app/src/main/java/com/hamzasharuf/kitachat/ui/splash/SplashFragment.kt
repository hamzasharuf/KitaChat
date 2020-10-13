package com.hamzasharuf.kitachat.ui.splash

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.hamzasharuf.kitachat.R
import com.hamzasharuf.kitachat.databinding.FragmentSplashBinding
import com.hamzasharuf.kitachat.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : BaseFragment<SplashViewModel, FragmentSplashBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigateToHome()
    }

    private fun navigateToHome() {
        lifecycleScope.launch(Dispatchers.Main) {
            delay(3000)
            findNavController().navigate(R.id.action_splashFragment_to_welcomeFragment)
        }
    }

    override fun getLayoutRes(): Int = R.layout.fragment_splash
}