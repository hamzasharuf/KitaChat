package com.hamzasharuf.kitachat.data.api.requests

import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

data class PhoneVerificationRequest (
    val phoneNumber: String,
    val timeoutDuration: Long,
    val timeUnit: TimeUnit,
    val activity: AppCompatActivity,
    val onVerificationStateChangedCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks,
)