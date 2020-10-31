package com.hamzasharuf.kitachat.ui.auth.setup

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.google.firebase.Timestamp
import com.hamzasharuf.kitachat.data.api.responses.UpdateUserProfileResponse
import com.hamzasharuf.kitachat.data.models.User
import com.hamzasharuf.kitachat.data.repositories.UserRepository
import com.hamzasharuf.kitachat.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class ProfileSetupViewModel @ViewModelInject constructor(
    private val repository: UserRepository,
) : BaseViewModel() {
    private val _updateUserProfileStatus = MutableLiveData<UpdateUserProfileResponse>()
    val updateUserProfileProfileStatus: LiveData<UpdateUserProfileResponse>
        get() = _updateUserProfileStatus


    fun updateUserProfile(userName: String) {
        _updateUserProfileStatus.value = UpdateUserProfileResponse.OnLoading
        val user = createUser(userName)
        updateFirestoreProfile(user)
        cacheUserToDatabase(user)
    }

    private fun createUser(userName: String): User {
        // TODO :: Logoff [send to register screen] if null
        val currentUser = repository.getCurrentUserApi() ?: throw IllegalAccessError("User not found")
        return User(
            userID = currentUser.uid,
            userName = userName,
            userPhone = currentUser.phoneNumber!!,
            imageProfile = "https://firebasestorage.googleapis.com/v0/b/kitachat-8e6d6.appspot.com/o/person_placeholder.jpg?alt=media&token=9ccc8f36-4f8b-4c1c-8ee4-07731f51bd33",
            status = "Hello there, I'm using KitaChat",
            registeredAt = Timestamp.now()
        )
    }

    private fun updateFirestoreProfile(user: User) {
        viewModelScope.launch(Main) {
            repository.updateUserProfile(user).asFlow().collect {
                _updateUserProfileStatus.value = it
            }
        }
    }

    private fun cacheUserToDatabase(user: User) {
        viewModelScope.launch(Main){ repository.insertUserCache(user) }
    }


}