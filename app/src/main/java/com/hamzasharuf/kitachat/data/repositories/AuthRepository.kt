package com.hamzasharuf.kitachat.data.repositories

import com.hamzasharuf.kitachat.data.api.auth.AuthApi
import com.hamzasharuf.kitachat.data.api.requests.PhoneAuthCredentialRequest
import com.hamzasharuf.kitachat.data.api.requests.PhoneVerificationRequest
import com.hamzasharuf.kitachat.data.api.requests.SignInWithPhoneCredentialRequest
import javax.inject.Inject

class AuthRepository @Inject constructor(private val authApi: AuthApi){

    fun verifyPhoneNumber(request: PhoneVerificationRequest) =
        authApi.verifyPhoneNumber(request)

    fun signInWithPhoneAuthCredential(request: SignInWithPhoneCredentialRequest) =
        authApi.signInWithPhoneAuthCredential(request)

    fun getPhoneAuthCredential(request: PhoneAuthCredentialRequest) =
        authApi.getPhoneAuthCredential(request)

}