package com.hamzasharuf.kitachat.data.repositories

import com.hamzasharuf.kitachat.data.api.auth.AuthApi
import com.hamzasharuf.kitachat.data.api.requests.PhoneVerificationRequest
import javax.inject.Inject

class AuthRepository @Inject constructor(private val authApi: AuthApi){

    fun verifyPhoneNumber(request: PhoneVerificationRequest) = authApi.verifyPhoneNumber(request)


}