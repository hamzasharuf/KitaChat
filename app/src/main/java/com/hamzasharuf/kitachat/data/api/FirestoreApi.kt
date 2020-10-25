package com.hamzasharuf.kitachat.data.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.hamzasharuf.kitachat.data.api.FirebaseConfig.USERS_COLLECTION
import com.hamzasharuf.kitachat.data.api.responses.UpdateUserProfileResponse
import com.hamzasharuf.kitachat.data.api.responses.UserInfoResponse
import com.hamzasharuf.kitachat.data.models.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.lang.NullPointerException
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

    fun getUserInfo(uid: String): LiveData<UserInfoResponse>{
        Log.d(TAG, "getUserInfo: uid --> $uid")
        val status = MutableLiveData <UserInfoResponse>()
        status.value = UserInfoResponse.OnLoading
        val document = firestore.collection(USERS_COLLECTION).document(uid)
        val get = document.get()

        get.addOnSuccessListener {snapshot ->
            Log.d(TAG, "getUserInfo: OnSucess")
            if (snapshot != null){
                val user = snapshot.toObject<User>()!!
                status.value = UserInfoResponse.OnSuccess(user)
            }else{
                status.value = UserInfoResponse.OnFailure(NullPointerException("data snapshot is null"))
            }
        }

        get.addOnFailureListener{
            status.value = UserInfoResponse.OnFailure(it)
            Log.d(TAG, "getUserInfo: OnFailure")
        }
        return status
    }

    companion object{
        private const val TAG = "FirestoreApi"
    }

}