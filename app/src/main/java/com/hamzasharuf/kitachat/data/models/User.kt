package com.hamzasharuf.kitachat.data.models

import com.google.firebase.Timestamp
import java.util.*

data class User(
    val userID: String,
    val userName: String,
    val userPhone: String,
    val imageProfile: String,
    val status: String,
    val registeredAt: Timestamp,
    val imageCover: String? = null,
    val email: String? = null,
    val dateOfBirth: String? = null,
    val gender: String? = null,
    )