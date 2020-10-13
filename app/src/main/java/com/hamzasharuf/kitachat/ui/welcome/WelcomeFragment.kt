package com.hamzasharuf.kitachat.ui.welcome

import android.os.Bundle
import android.view.View
import com.hamzasharuf.kitachat.R
import com.hamzasharuf.kitachat.databinding.FragmentWelcomeBinding
import com.hamzasharuf.kitachat.ui.base.BaseFragment


class WelcomeFragment : BaseFragment<WelcomeViewModel, FragmentWelcomeBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun getLayoutRes() = R.layout.fragment_welcome
}