package com.hamzasharuf.kitachat.ui.splash

import androidx.hilt.lifecycle.ViewModelInject
import com.hamzasharuf.kitachat.data.repositories.UserRepository
import com.hamzasharuf.kitachat.ui.base.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class SplashViewModel @ViewModelInject constructor(
    private val repository: UserRepository
) : BaseViewModel() {

    val isRegistered by lazy { repository.getCurrentUser() != null }

}