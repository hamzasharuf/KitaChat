package com.hamzasharuf.kitachat.data.api.responses

import com.hamzasharuf.kitachat.data.models.User

sealed class UserInfoResponse {
    data class OnSuccess(val user: User): UserInfoResponse()
    data class OnFailure(val exception: Exception) : UserInfoResponse()
    object OnLoading : UserInfoResponse()
}