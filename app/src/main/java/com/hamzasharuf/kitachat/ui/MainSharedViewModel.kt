package com.hamzasharuf.kitachat.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.hamzasharuf.kitachat.data.models.User
import com.hamzasharuf.kitachat.data.repositories.UserRepository
import com.hamzasharuf.kitachat.ui.base.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class MainSharedViewModel @ViewModelInject constructor(
    private val userRepository: UserRepository
) : BaseViewModel() {

    var cachedUser: User? = null

    fun getCachedUser() {
        viewModelScope.launch{
            val result = userRepository.getUserCache()
            if (result.isNotEmpty())
                cachedUser = result.first()
        }
    }

    val firebaseUser = userRepository.getCurrentUserApi()

}