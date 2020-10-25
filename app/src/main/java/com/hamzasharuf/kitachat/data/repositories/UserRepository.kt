package com.hamzasharuf.kitachat.data.repositories

import androidx.lifecycle.LiveData
import com.hamzasharuf.kitachat.data.api.FirestoreApi
import com.hamzasharuf.kitachat.data.api.UserApi
import com.hamzasharuf.kitachat.data.api.responses.UpdateUserProfileResponse
import com.hamzasharuf.kitachat.data.models.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class UserRepository @Inject constructor(
    private val firestoreApi: FirestoreApi,
    private val userApi: UserApi
) {
    fun updateUserProfile(user: User):LiveData<UpdateUserProfileResponse>{
        return firestoreApi.updateUserProfile(user)
    }

    fun getCurrentUser() = userApi.getCurrentUser()

    fun getUserInfo(uid: String) = firestoreApi.getUserInfo(uid)
}