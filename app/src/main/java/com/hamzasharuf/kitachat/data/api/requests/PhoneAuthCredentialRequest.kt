package com.hamzasharuf.kitachat.data.api.requests

data class PhoneAuthCredentialRequest(
    val verificationId: String,
    val code: String,
)