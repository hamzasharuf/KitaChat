package com.hamzasharuf.kitachat.utils.adapters.list.calls

import androidx.recyclerview.widget.DiffUtil
import com.hamzasharuf.kitachat.data.models.Call

class CallsDiffUtil  : DiffUtil.ItemCallback<Call>(){
    override fun areItemsTheSame(oldItem: Call, newItem: Call) = oldItem == newItem

    override fun areContentsTheSame(oldItem: Call, newItem: Call) =
        (oldItem.userId == newItem.userId) && (oldItem.date == newItem.date)

}