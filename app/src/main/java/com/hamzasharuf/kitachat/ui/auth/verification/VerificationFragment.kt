package com.hamzasharuf.kitachat.ui.auth.verification

import android.os.Bundle
import android.view.View
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
import com.hamzasharuf.kitachat.utils.common.AppFunctions.getVerificationMessage
import com.hamzasharuf.kitachat.utils.extensions.setInvisible
import com.hamzasharuf.kitachat.utils.extensions.setVisible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_verification.*
import kotlinx.coroutines.InternalCoroutinesApi
import timber.log.Timber
import java.lang.NullPointerException

@InternalCoroutinesApi
@AndroidEntryPoint
class VerificationFragment : BaseFragment<AuthViewModel, FragmentVerificationBinding>() {

    private val viewModel: AuthViewModel by viewModels()
    val args: VerificationFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("phone_number -> ${args.phoneNumber}, verificationId -> ${args.verificationId}")
        viewModel.phoneNumber = args.phoneNumber
        viewModel.mVerificationId = args.verificationId
        tv_login_warning.text = getVerificationMessage(viewModel.phoneNumber)
        tv_login_phone_number.text = viewModel.phoneNumber
        setupClickListeners()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.status.observe(requireActivity(), {
            when (it) {
                is SignInWithPhoneResponse.OnSuccess -> {
                    Timber.d("VerificationFragment -> Success")
                    progress_bar.setInvisible()
                    findNavController().navigate(R.id.action_verificationFragment_to_homeFragment)
                }
                is SignInWithPhoneResponse.OnFailure -> {
                    progress_bar.setInvisible()
                    btnVerify.isEnabled = true
                    Timber.e("VerificationFragment -> Failure -> ${it.exception}")
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
                    Timber.d("VerificationFragment -> Loading")
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