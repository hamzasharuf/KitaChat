package com.hamzasharuf.kitachat.ui.login

import android.os.Bundle
import android.view.View
import com.hamzasharuf.kitachat.R
import com.hamzasharuf.kitachat.databinding.FragmentLoginBinding
import com.hamzasharuf.kitachat.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : BaseFragment<LoginViewModel, FragmentLoginBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun getLayoutRes() = R.layout.fragment_login
}