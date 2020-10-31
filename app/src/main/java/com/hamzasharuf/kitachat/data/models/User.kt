package com.hamzasharuf.kitachat.data.models

import com.google.firebase.Timestamp

data class User(
    val userID: String = "",
    val userName: String = "",
    val userPhone: String = "",
    val imageProfile: String = "",
    val status: String = "",
    val gender: String = "male",
    val imageCover: String = "",
    val email: String = "",
    val dateOfBirth: String = "",
    val registeredAt: Timestamp = Timestamp.now(),
)