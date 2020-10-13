package com.hamzasharuf.kitachat.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hamzasharuf.kitachat.ui.base.BaseViewModel
import com.hamzasharuf.kitachat.utils.adapters.HomeSection

class HomeViewModel @ViewModelInject constructor() : BaseViewModel() {

    private val _pagerState = MutableLiveData<HomeSection>()
    val pagerState: LiveData<HomeSection>
        get() = _pagerState

    fun setPagerState(position: Int) { _pagerState.value = HomeSection.values()[position] }


}