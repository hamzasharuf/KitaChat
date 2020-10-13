package com.hamzasharuf.kitachat.utils.adapters.list.chats

import androidx.recyclerview.widget.DiffUtil
import com.hamzasharuf.kitachat.data.models.Chat

class ChatDiffUtil  : DiffUtil.ItemCallback<Chat>(){

    override fun areItemsTheSame(oldItem: Chat, newItem: Chat) = oldItem == newItem

    override fun areContentsTheSame(oldItem: Chat, newItem: Chat) = oldItem.userId == newItem.userId
}