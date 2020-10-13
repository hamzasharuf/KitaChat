package com.hamzasharuf.kitachat.utils.adapters.list.chats

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hamzasharuf.kitachat.data.models.Chat
import java.lang.IllegalArgumentException

class ChatsListAdapter(private val clickListener: ChatsClickListener)
    : ListAdapter<Chat, RecyclerView.ViewHolder>(ChatDiffUtil()) {

    companion object{
        const val CHAT = 1
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            CHAT -> ChatViewHolder.from(parent)
            else -> throw IllegalArgumentException("Unknown viewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is ChatViewHolder -> holder.bind(getItem(position), clickListener, position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is Chat -> CHAT
            else -> throw IllegalArgumentException("Unknown Item ${getItem(position)}")
        }
    }
}