package com.hamzasharuf.kitachat.utils.adapters.list.chats

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hamzasharuf.kitachat.data.models.Chat
import com.hamzasharuf.kitachat.databinding.ListItemChatBinding

class ChatViewHolder private constructor(val binding: ListItemChatBinding) :

    RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Chat, clickListener: ChatsClickListener, position: Int) {
            binding.clickListener = clickListener
            binding.item = item
            binding.position = position
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ChatViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemChatBinding.inflate(layoutInflater, parent, false)
                return ChatViewHolder(binding)
            }
        }

}