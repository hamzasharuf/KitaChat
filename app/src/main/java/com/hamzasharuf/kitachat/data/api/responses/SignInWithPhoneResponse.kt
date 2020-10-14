package com.hamzasharuf.kitachat.data.api.responses

import com.google.firebase.auth.FirebaseUser
import java.lang.Exception

sealed class SignInWithPhoneResponse {
    data class OnSuccess(val user: FirebaseUser): SignInWithPhoneResponse()
    data class OnFailure(val exception: Exception) : SignInWithPhoneResponse()
    object OnLoading : SignInWithPhoneResponse()
}