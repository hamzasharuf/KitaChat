package com.hamzasharuf.kitachat.ui.auth

import android.app.Activity
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.hamzasharuf.kitachat.data.api.requests.PhoneAuthCredentialRequest
import com.hamzasharuf.kitachat.data.api.requests.PhoneVerificationRequest
import com.hamzasharuf.kitachat.data.api.requests.SignInWithPhoneCredentialRequest
import com.hamzasharuf.kitachat.data.api.responses.SignInWithPhoneResponse
import com.hamzasharuf.kitachat.data.repositories.AuthRepository
import com.hamzasharuf.kitachat.ui.base.BaseViewModel
import java.lang.NullPointerException
import java.util.concurrent.TimeUnit

class AuthViewModel @ViewModelInject constructor(
    private val authRepository: AuthRepository
) : BaseViewModel() {

    private var mVerificationId: String? = null
    var phoneNumber = ""

    var _status: MutableLiveData<SignInWithPhoneResponse> = MutableLiveData()
    val status: LiveData<SignInWithPhoneResponse>
        get() = _status

    private val _verificationStatus = MutableLiveData<VerificationStatus>()
    val verificationStatus: LiveData<VerificationStatus>
        get() = _verificationStatus

    fun verifyPhoneNumber(activity: Activity) {
        _verificationStatus.value = VerificationStatus.Processing
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

    fun signInWithPhoneAuthCredential(code: String, activity: Activity) {
        _status.value = SignInWithPhoneResponse.OnLoading
        if (mVerificationId != null) {

            // 1 - Create PhoneAuthCredential
            val credential = createPhoneAuthCredentials(code)

            // 2 - Sign In using the credential
            _status = authRepository.signInWithPhoneAuthCredential(
                SignInWithPhoneCredentialRequest(credential, activity)
            ) as MutableLiveData<SignInWithPhoneResponse>
        } else {
            _status.value = SignInWithPhoneResponse.OnFailure(NullPointerException("mVerificationId is null"))
        }
    }

    private val onVerificationStateChangedCallbacks =
        object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            /**
             * ( Automatic Verification )
             * This method is called in two situations:
             *
             * - Instant verification: in some cases the phone number can be instantly verified without needing to send or enter a verification code.
             * - Auto-retrieval: on some devices, Google Play services can automatically detect the incoming verification SMS and perform verification without user action. (This capability might be unavailable with some carriers.)
             *
             * In either case, the user's phone number has been verified successfully, and you can use the PhoneAuthCredential object that's passed to the callback to sign in the user.
             */
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                _verificationStatus.value = VerificationStatus.Success
            }

            /**
             * This method is called in response to an invalid verification request, such as a request that specifies an invalid phone number or verification code.
             */
            override fun onVerificationFailed(exception: FirebaseException) {
                _verificationStatus.value = VerificationStatus.Failed(exception)
            }

            /**
             * Optional. This method is called after the verification code has been sent by SMS to the provided phone number.
             */
            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                super.onCodeSent(verificationId, token)
                mVerificationId = verificationId
            }

            /**
             * Optional. This method is called after the timeout duration specified to verifyPhoneNumber has passed without onVerificationCompleted triggering first. On devices without SIM cards, this method is called immediately because SMS auto-retrieval isn't possible.
             */
            override fun onCodeAutoRetrievalTimeOut(verificationId: String) {
                super.onCodeAutoRetrievalTimeOut(verificationId)
                mVerificationId = verificationId
            }
        }

    private fun createPhoneAuthCredentials(code: String) = authRepository.getPhoneAuthCredential(
        PhoneAuthCredentialRequest(mVerificationId!!, code)
    )

}