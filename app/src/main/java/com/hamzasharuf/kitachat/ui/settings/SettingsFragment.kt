package com.hamzasharuf.kitachat.ui.settings

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hamzasharuf.kitachat.R
import com.hamzasharuf.kitachat.data.api.responses.UserInfoResponse
import com.hamzasharuf.kitachat.databinding.FragmentSettingsBinding
import com.hamzasharuf.kitachat.ui.MainSharedViewModel
import com.hamzasharuf.kitachat.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class SettingsFragment : BaseFragment<SettingsViewModel, FragmentSettingsBinding>() {

    private val viewModel: SettingsViewModel by viewModels()

    private val sharedViewModel: MainSharedViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupClickListeners()
    }

    private fun setupUI() {
        val user = sharedViewModel.cachedUser!!
        binding.user = user
        binding.executePendingBindings()
    }

    private fun setupClickListeners() {
        ln_profile.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_profileFragment)
        }
    }

    override fun getLayoutRes() = R.layout.fragment_settings

}