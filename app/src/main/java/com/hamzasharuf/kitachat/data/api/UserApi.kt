package com.hamzasharuf.kitachat.data.api

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class UserApi @Inject constructor() {

    fun getCurrentUser() = FirebaseAuth.getInstance().currentUser

}