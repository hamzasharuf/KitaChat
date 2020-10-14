package com.hamzasharuf.kitachat.ui.login

import androidx.appcompat.app.AppCompatActivity
import androidx.hilt.lifecycle.ViewModelInject
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.hamzasharuf.kitachat.data.api.requests.PhoneVerificationRequest
import com.hamzasharuf.kitachat.data.repositories.AuthRepository
import com.hamzasharuf.kitachat.ui.base.BaseViewModel
import java.util.concurrent.TimeUnit

class LoginViewModel @ViewModelInject constructor(
    val authRepository: AuthRepository
) : BaseViewModel() {

    fun verifyPhoneNumber(phoneNumber: String, activity: AppCompatActivity) {
        authRepository.verifyPhoneNumber(
            PhoneVerificationRequest(
                phoneNumber = phoneNumber,
                timeoutDuration = 60,
                timeUnit = TimeUnit.SECONDS,
                activity = activity,
                onVerificationStateChangedCallbacks = onVerificationStateChangedCallbacks
            )
        )
    }

    private val onVerificationStateChangedCallbacks =
        object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            /**
             * This method is called in two situations:
             *
             * - Instant verification: in some cases the phone number can be instantly verified without needing to send or enter a verification code.
             * - Auto-retrieval: on some devices, Google Play services can automatically detect the incoming verification SMS and perform verification without user action. (This capability might be unavailable with some carriers.)
             *
             * In either case, the user's phone number has been verified successfully, and you can use the PhoneAuthCredential object that's passed to the callback to sign in the user.
             */
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                TODO("Not yet implemented")
            }

            /**
             * This method is called in response to an invalid verification request, such as a request that specifies an invalid phone number or verification code.
             */
            override fun onVerificationFailed(exception: FirebaseException) {
                when (exception) {
                    is FirebaseAuthInvalidCredentialsException -> {
                        // Invalid request
                        // ...
                    }
                    is FirebaseTooManyRequestsException -> {
                        // The SMS quota for the project has been exceeded
                        // ...
                    }
                }
            }

            /**
             * Optional. This method is called after the verification code has been sent by SMS to the provided phone number.
             */
            override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(verificationId, token)
            }

            /**
             * Optional. This method is called after the timeout duration specified to verifyPhoneNumber has passed without onVerificationCompleted triggering first. On devices without SIM cards, this method is called immediately because SMS auto-retrieval isn't possible.
             */
            override fun onCodeAutoRetrievalTimeOut(verificationId: String) {
                super.onCodeAutoRetrievalTimeOut(verificationId)
            }
        }
}