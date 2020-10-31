package com.hamzasharuf.kitachat.ui.settings

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hamzasharuf.kitachat.R
import com.hamzasharuf.kitachat.data.api.responses.UserInfoResponse
import com.hamzasharuf.kitachat.databinding.FragmentSettingsBinding
import com.hamzasharuf.kitachat.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class SettingsFragment : BaseFragment<SettingsViewModel, FragmentSettingsBinding>() {

    private val viewModel: SettingsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        viewModel.getUserInfo()
        setupClickListeners()
    }

    private fun setupClickListeners() {
        ln_profile.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_profileFragment)
        }
    }

    private fun setupObservers() {
        viewModel.userInfoStatus.observe(viewLifecycleOwner, {
            when (it) {
                is UserInfoResponse.OnSuccess -> {
                    binding.user = it.user
                    binding.executePendingBindings()
                }
                is UserInfoResponse.OnFailure -> {
                    Toast.makeText(requireContext(), "Unexpected Error", Toast.LENGTH_SHORT).show()
                }
                UserInfoResponse.OnLoading -> {
                    // Don't care
                }
            }
        })
    }

    override fun getLayoutRes() = R.layout.fragment_settings

}