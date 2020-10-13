package com.hamzasharuf.kitachat.data.models

import com.hamzasharuf.kitachat.utils.adapters.list.calls.CallType

data class Call(
    val userId: String,
    val userName: String,
    val date: String,
    val urlProfile: String,
    val callType: CallType,
)