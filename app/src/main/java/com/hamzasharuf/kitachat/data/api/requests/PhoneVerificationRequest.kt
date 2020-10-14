package com.hamzasharuf.kitachat.data.api.requests

import android.app.Activity
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

data class PhoneVerificationRequest (
    val phoneNumber: String,
    val timeoutDuration: Long,
    val timeUnit: TimeUnit,
    val activity: Activity,
    val onVerificationStateChangedCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks,
)