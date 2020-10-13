package com.hamzasharuf.kitachat.utils.adapters.list.calls

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hamzasharuf.kitachat.data.models.Call
import com.hamzasharuf.kitachat.data.models.Chat
import com.hamzasharuf.kitachat.databinding.ListItemCallBinding
import com.hamzasharuf.kitachat.databinding.ListItemChatBinding

class CallViewHolder private constructor(val binding: ListItemCallBinding) :

    RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Call, clickListener: CallsClickListener, position: Int) {
            binding.clickListener = clickListener
            binding.item = item
            binding.position = position
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): CallViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemCallBinding.inflate(layoutInflater, parent, false)
                return CallViewHolder(binding)
            }
        }

}