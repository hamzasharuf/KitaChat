package com.hamzasharuf.kitachat.ui.auth.setup

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hamzasharuf.kitachat.R
import com.hamzasharuf.kitachat.data.api.responses.UpdateUserProfileResponse
import com.hamzasharuf.kitachat.databinding.FragmentProfileSetupBinding
import com.hamzasharuf.kitachat.ui.base.BaseFragment
import com.hamzasharuf.kitachat.utils.extensions.setInvisible
import com.hamzasharuf.kitachat.utils.extensions.setVisible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_profile_setup.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class ProfileSetupFragment : BaseFragment<ProfileSetupViewModel, FragmentProfileSetupBinding>() {

    private val viewModel: ProfileSetupViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setClickListeners()
    }

    private fun setClickListeners() {
        btnNext.setOnClickListener {
            updateUserProfile()
        }
    }

    private fun updateUserProfile() {
        val userName = etName.text.toString()
        if (userName.isBlank()){
            Toast.makeText(requireContext(), "Enter your name", Toast.LENGTH_SHORT).show()
            return
        }
        viewModel.updateUserProfile(userName)
    }

    private fun setupObservers() {
        viewModel.updateUserProfileProfileStatus.observe(viewLifecycleOwner, {
            when (it) {
                UpdateUserProfileResponse.OnLoading -> {
                    progress_bar.setVisible()
                    btnNext.isEnabled = false
                }
                UpdateUserProfileResponse.OnSuccess -> {
                    progress_bar.setInvisible()
                    btnNext.isEnabled = true
                    Toast.makeText(requireContext(), "Saved successfully", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_profileSetupFragment_to_homeFragment)
                }
                is UpdateUserProfileResponse.OnFailure -> {
                    progress_bar.setInvisible()
                    btnNext.isEnabled = true
                    Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun getLayoutRes() = R.layout.fragment_profile_setup

}