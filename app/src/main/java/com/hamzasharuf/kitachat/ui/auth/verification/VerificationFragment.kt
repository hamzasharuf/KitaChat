package com.hamzasharuf.kitachat.ui.auth.verification

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.hamzasharuf.kitachat.R
import com.hamzasharuf.kitachat.data.api.responses.SignInWithPhoneResponse
import com.hamzasharuf.kitachat.databinding.FragmentVerificationBinding
import com.hamzasharuf.kitachat.ui.auth.AuthViewModel
import com.hamzasharuf.kitachat.ui.base.BaseFragment
import com.hamzasharuf.kitachat.utils.common.AppConstants.TAG
import com.hamzasharuf.kitachat.utils.common.AppFunctions.getVerificationMessage
import com.hamzasharuf.kitachat.utils.extensions.setInvisible
import com.hamzasharuf.kitachat.utils.extensions.setVisible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_verification.*
import kotlinx.android.synthetic.main.fragment_verification.progress_bar
import kotlinx.android.synthetic.main.fragment_verification.tv_profile_message
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@AndroidEntryPoint
class VerificationFragment : BaseFragment<AuthViewModel, FragmentVerificationBinding>() {

    private val viewModel: AuthViewModel by viewModels()
    val args: VerificationFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModelValues()
        setupUi()
        setupClickListeners()
        setupObservers()
        setupVerificationTextWatchers()
    }

    private fun initViewModelValues() {
        viewModel.phoneNumber = args.phoneNumber
        viewModel.mVerificationId = args.verificationId
    }

    private fun setupUi() {
        tv_profile_message.text = getVerificationMessage(viewModel.phoneNumber)
        tv_login_phone_number.text = viewModel.phoneNumber
    }

    private fun setupObservers() {
        viewModel.status.observe(requireActivity(), {
            when (it) {
                is SignInWithPhoneResponse.OnSuccess -> {
                    progress_bar.setInvisible()
                    findNavController().navigate(R.id.action_verificationFragment_to_profileSetupFragment)
                }
                is SignInWithPhoneResponse.OnFailure -> {
                    progress_bar.setInvisible()
                    btnVerify.isEnabled = true
                    when (it.exception) {
                        is FirebaseAuthInvalidCredentialsException ->
                            Toast.makeText(
                                requireContext(),
                                "Invalid verification code",
                                Toast.LENGTH_SHORT
                            ).show()
                        is NullPointerException ->
                            Toast.makeText(
                                requireContext(),
                                "something went wrong, try again later",
                                Toast.LENGTH_SHORT
                            ).show()
                        else ->
                            Toast.makeText(
                                requireContext(),
                                "something went wrong, try again later",
                                Toast.LENGTH_SHORT
                            ).show()
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

        if (!codeValidation()) {
            Toast.makeText(requireContext(), "Enter the Verification code", Toast.LENGTH_SHORT)
                .show()
            return
        }
        val code = buildString {
            append(
                etCode1.text,
                etCode2.text,
                etCode3.text,
                etCode4.text,
                etCode5.text,
                etCode6.text
            )
        }
        Log.d(TAG, "verifyCode: my verification code --> $code")
        viewModel.signInWithPhoneAuthCredential(code, requireActivity())
    }

    private fun setupVerificationTextWatchers() {

        etCode1.requestFocus()
        val imm = requireContext().getSystemService(INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.showSoftInput(etCode1, InputMethodManager.SHOW_IMPLICIT)

        // GenericTextWatcher here works only for moving to next EditText when a number is entered
        // first parameter is the current EditText and second parameter is next EditText
        etCode1.addTextChangedListener(GenericTextWatcher(etCode1, etCode2))
        etCode2.addTextChangedListener(GenericTextWatcher(etCode2, etCode3))
        etCode3.addTextChangedListener(GenericTextWatcher(etCode3, etCode4))
        etCode4.addTextChangedListener(GenericTextWatcher(etCode4, etCode5))
        etCode5.addTextChangedListener(GenericTextWatcher(etCode5, etCode6))
        etCode6.addTextChangedListener(GenericTextWatcher(etCode6, null))

        // GenericKeyEvent here works for deleting the element and to switch back to previous EditText
        // first parameter is the current EditText and second parameter is previous EditText
        etCode1.setOnKeyListener(GenericKeyEvent(etCode1, null))
        etCode2.setOnKeyListener(GenericKeyEvent(etCode2, etCode1))
        etCode3.setOnKeyListener(GenericKeyEvent(etCode3, etCode2))
        etCode4.setOnKeyListener(GenericKeyEvent(etCode4, etCode3))
        etCode5.setOnKeyListener(GenericKeyEvent(etCode5, etCode4))
        etCode6.setOnKeyListener(GenericKeyEvent(etCode6, etCode5))
    }

    private fun codeValidation() = !etCode1.text.isNullOrBlank() &&
            !etCode2.text.isNullOrBlank() &&
            !etCode3.text.isNullOrBlank() &&
            !etCode4.text.isNullOrBlank() &&
            !etCode5.text.isNullOrBlank() &&
            !etCode6.text.isNullOrBlank()


    override fun getLayoutRes() = R.layout.fragment_verification

}