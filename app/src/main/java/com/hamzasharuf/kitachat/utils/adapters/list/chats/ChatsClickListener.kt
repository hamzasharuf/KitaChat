package com.hamzasharuf.kitachat.utils.adapters.list.chats

import com.hamzasharuf.kitachat.data.models.Chat

class ChatsClickListener (val clickListener: (item: Chat, position: Int) -> Unit) {
    fun onClick(item: Chat, position: Int) = clickListener(item, position)
}