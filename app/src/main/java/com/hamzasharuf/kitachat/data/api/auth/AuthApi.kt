package com.hamzasharuf.kitachat.data.api.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider
import com.hamzasharuf.kitachat.data.api.requests.PhoneAuthCredentialRequest
import com.hamzasharuf.kitachat.data.api.requests.PhoneVerificationRequest
import com.hamzasharuf.kitachat.data.api.requests.SignInWithPhoneCredentialRequest
import com.hamzasharuf.kitachat.data.api.responses.SignInWithPhoneResponse
import javax.inject.Inject

class AuthApi @Inject constructor() {

    fun verifyPhoneNumber(request: PhoneVerificationRequest) {
        with(request) {
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,
                timeoutDuration,
                timeUnit,
                activity,
                onVerificationStateChangedCallbacks
            )

        }
    }

    fun signInWithPhoneAuthCredential(request: SignInWithPhoneCredentialRequest)
            : LiveData<SignInWithPhoneResponse> {
        val auth = FirebaseAuth.getInstance()
        val status = MutableLiveData<SignInWithPhoneResponse>()
        auth.signInWithCredential(request.credential)
            .addOnCompleteListener(request.activity) { task ->
                if (task.isSuccessful) {
                    val user = task.result?.user
                    status.value = user?.let { SignInWithPhoneResponse.OnSuccess(it) }
                } else {
                    status.value = task.exception?.let { SignInWithPhoneResponse.OnFailure(it) }
                }
            }
        return status
    }

    fun getPhoneAuthCredential(request: PhoneAuthCredentialRequest) =
        PhoneAuthProvider.getCredential(request.verificationId, request.code)





}