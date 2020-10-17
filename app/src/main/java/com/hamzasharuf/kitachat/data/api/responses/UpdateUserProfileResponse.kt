package com.hamzasharuf.kitachat.data.api.responses

import java.lang.Exception

sealed class UpdateUserProfileResponse {
    object OnSuccess: UpdateUserProfileResponse()
    data class OnFailure(val exception: Exception) : UpdateUserProfileResponse()
    object OnLoading : UpdateUserProfileResponse()
}