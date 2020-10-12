package com.hamzasharuf.kitachat.utils.adapters

enum class HomeSection() {
    CHATS,
    STATUS,
    CALLS;

    companion object {
        fun getItem(position: Int): HomeSection = values()[position]
    }
}