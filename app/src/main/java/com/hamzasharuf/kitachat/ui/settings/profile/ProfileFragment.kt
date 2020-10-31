package com.hamzasharuf.kitachat.ui.settings.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.hamzasharuf.kitachat.R
import com.hamzasharuf.kitachat.databinding.FragmentProfileBinding
import com.hamzasharuf.kitachat.ui.MainSharedViewModel
import com.hamzasharuf.kitachat.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.tv_username
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class ProfileFragment : BaseFragment<ProfileViewModel, FragmentProfileBinding>() {

    private val viewModel: ProfileViewModel by viewModels()

    @ExperimentalCoroutinesApi
    private val sharedViewModel: MainSharedViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        tv_username.text = sharedViewModel.cachedUser?.userName
        tv_phone.text = sharedViewModel.cachedUser?.userPhone
        tv_bio.text = sharedViewModel.cachedUser?.status
    }

    override fun getLayoutRes() = R.layout.fragment_profile

}