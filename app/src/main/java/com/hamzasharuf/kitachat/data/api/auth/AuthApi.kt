package com.hamzasharuf.kitachat.data.api.auth

import com.google.firebase.auth.PhoneAuthProvider
import com.hamzasharuf.kitachat.data.api.requests.PhoneVerificationRequest
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



}