package com.hamzasharuf.kitachat.data.api.requests

import android.app.Activity
import com.google.firebase.auth.PhoneAuthCredential

data class SignInWithPhoneCredentialRequest(
    val credential: PhoneAuthCredential,
    val activity: Activity,
)