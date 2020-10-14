package com.hamzasharuf.kitachat.ui.auth.verification

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.hamzasharuf.kitachat.R
import com.hamzasharuf.kitachat.data.api.responses.SignInWithPhoneResponse
import com.hamzasharuf.kitachat.databinding.FragmentVerificationBinding
import com.hamzasharuf.kitachat.ui.auth.AuthViewModel
import com.hamzasharuf.kitachat.ui.base.BaseFragment
import com.hamzasharuf.kitachat.utils.common.AppFunctions.getVerificationMessage
import com.hamzasharuf.kitachat.utils.extensions.setInvisible
import com.hamzasharuf.kitachat.utils.extensions.setVisible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_verification.*
import java.lang.NullPointerException

@AndroidEntryPoint
class VerificationFragment : BaseFragment<AuthViewModel, FragmentVerificationBinding>() {

    private val viewModel: AuthViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_login_phone_number.text = getVerificationMessage(viewModel.phoneNumber)
        setupClickListeners()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.status.observe(viewLifecycleOwner, {
            when (it) {
                is SignInWithPhoneResponse.OnSuccess -> {
                    progress_bar.setInvisible()
                    findNavController().navigate(R.id.action_verificationFragment_to_homeFragment)
                }
                is SignInWithPhoneResponse.OnFailure -> {
                    progress_bar.setInvisible()
                    btnVerify.isEnabled = true
                    when (it.exception) {
                        is FirebaseAuthInvalidCredentialsException ->
                            Toast.makeText(requireContext(), "Invalid verification code", Toast.LENGTH_SHORT).show()
                        is NullPointerException ->
                            Toast.makeText(requireContext(), "something went wrong, try again later", Toast.LENGTH_SHORT).show()
                        else ->
                            Toast.makeText(requireContext(), "something went wrong, try again later", Toast.LENGTH_SHORT).show()
                    }
                }
                SignInWithPhoneResponse.OnLoading -> {
                    progress_bar.setVisible()
                    btnVerify.isEnabled = false
                }
            }
        })
    }

    private fun setupClickListeners() {
        btnVerify.setOnClickListener {
            verifyCode()
        }
    }

    private fun verifyCode() {
        val code: String? = etCode.text.toString()
        if (code.isNullOrBlank() || code.length < 6) {
            Toast.makeText(requireContext(), "Enter the code to proceed", Toast.LENGTH_SHORT).show()
            return
        }
        viewModel.signInWithPhoneAuthCredential(code, requireActivity())
    }


    override fun getLayoutRes() = R.layout.fragment_verification

}