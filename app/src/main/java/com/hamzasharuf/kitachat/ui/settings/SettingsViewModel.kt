package com.hamzasharuf.kitachat.ui.settings

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.hamzasharuf.kitachat.data.api.responses.UserInfoResponse
import com.hamzasharuf.kitachat.data.repositories.UserRepository
import com.hamzasharuf.kitachat.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class SettingsViewModel @ViewModelInject constructor(
    private val userRepository: UserRepository,
): BaseViewModel() {

    private val _userInfoStatus = MutableLiveData<UserInfoResponse>()
    val userInfoStatus: LiveData<UserInfoResponse>
        get() = _userInfoStatus

    fun getUserInfo(){
        viewModelScope.launch(Main){
            val uid: String = userRepository.getCurrentUser()!!.uid
            userRepository.getUserInfo(uid).asFlow().collect {
                _userInfoStatus.value = it
            }
        }
    }

}