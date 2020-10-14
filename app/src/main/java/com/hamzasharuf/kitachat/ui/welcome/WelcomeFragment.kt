package com.hamzasharuf.kitachat.ui.welcome

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.hamzasharuf.kitachat.R
import com.hamzasharuf.kitachat.databinding.FragmentWelcomeBinding
import com.hamzasharuf.kitachat.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_welcome.*


class WelcomeFragment : BaseFragment<WelcomeViewModel, FragmentWelcomeBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListeners()
    }

    private fun setClickListeners() {
        btnAgree.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_loginFragment)
        }
    }

    override fun getLayoutRes() = R.layout.fragment_welcome
}