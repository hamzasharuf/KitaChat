package com.hamzasharuf.kitachat.utils.adapters.list.calls

import com.hamzasharuf.kitachat.data.models.Call

class CallsClickListener (val clickListener: (item: Call, position: Int) -> Unit) {
    fun onClick(item: Call, position: Int) = clickListener(item, position)
}