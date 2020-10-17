package com.hamzasharuf.kitachat.data.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.hamzasharuf.kitachat.data.api.FirebaseConfig.USERS_COLLECTION
import com.hamzasharuf.kitachat.data.api.responses.UpdateUserProfileResponse
import com.hamzasharuf.kitachat.data.models.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

class FirestoreApi @Inject constructor() {

    private val firestore = FirebaseFirestore.getInstance()

    @ExperimentalCoroutinesApi
    fun updateUserProfile(user: User): LiveData<UpdateUserProfileResponse> {
        val status = MutableLiveData <UpdateUserProfileResponse>()
        status.value = UpdateUserProfileResponse.OnLoading
        val document = firestore.collection(USERS_COLLECTION).document(user.userID)
        val set = document.set(user)
        set.addOnSuccessListener {
            status.value = UpdateUserProfileResponse.OnSuccess
        }
        set.addOnFailureListener {
            status.value =UpdateUserProfileResponse.OnFailure(it)
        }
        return status
    }

    companion object{
        private const val TAG = "FirestoreApi"
    }

}