package com.hamzasharuf.kitachat.ui.auth.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.hamzasharuf.kitachat.R
import com.hamzasharuf.kitachat.databinding.FragmentLoginBinding
import com.hamzasharuf.kitachat.ui.auth.AuthViewModel
import com.hamzasharuf.kitachat.ui.auth.VerificationStatus
import com.hamzasharuf.kitachat.ui.base.BaseFragment
import com.hamzasharuf.kitachat.utils.extensions.setInvisible
import com.hamzasharuf.kitachat.utils.extensions.setVisible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.coroutines.InternalCoroutinesApi
import timber.log.Timber


@InternalCoroutinesApi
@AndroidEntryPoint
class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding>() {

    private val viewModel: AuthViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        setupObservers()

    }

    private fun setupObservers() {
        viewModel.verificationStatus.observe(requireActivity(), {
            when (it) {
                VerificationStatus.Processing -> {
                    progress_bar.setVisible()
                    btnNext.isEnabled = false
                    Timber.d("Processing")
                }
                VerificationStatus.Pending -> {

                    progress_bar.setInvisible()
                    btnNext.isEnabled = true
                    Timber.d("Pending: phone number -> ${viewModel.phoneNumber}")
                    val navigationAction = LoginFragmentDirections.actionLoginFragmentToVerificationFragment(viewModel.phoneNumber, viewModel.mVerificationId!!)
                    findNavController().navigate(navigationAction)
                }
                VerificationStatus.Success -> {
                    progress_bar.setInvisible()
                    btnNext.isEnabled = true
                    Timber.d("Success")
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                }
                is VerificationStatus.Failed -> {
                    progress_bar.setInvisible()
                    btnNext.isEnabled = true
                    when (it.exception) {
                        is FirebaseAuthInvalidCredentialsException ->
                            Toast.makeText(requireContext(), "Invalid Phone number: ${viewModel.phoneNumber}", Toast.LENGTH_SHORT).show()
                        is FirebaseTooManyRequestsException ->
                            Toast.makeText(requireContext(), "Something went wrong, try again later", Toast.LENGTH_SHORT).show()
                        else ->{
                            Timber.d("Something went wrong: ${it.exception}")
                            Toast.makeText(requireContext(), "Something went wrong, try again later", Toast.LENGTH_SHORT).show()
                            }
                    }
                }
            }
        })
    }

    private fun setupClickListeners() {
        btnNext.setOnClickListener {
            verifyPhoneNumber()
        }
    }

    private fun verifyPhoneNumber() {
        if (etCountryCode.text.isNullOrBlank() || etPhoneNumber.text.isNullOrBlank()) {
            // TODO : Enhance the validation
            Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }
        val phoneNumber = "+${etCountryCode.text}${etPhoneNumber.text}"
        viewModel.phoneNumber = phoneNumber
        viewModel.verifyPhoneNumber(requireActivity())
    }

    override fun getLayoutRes() = R.layout.fragment_login
}