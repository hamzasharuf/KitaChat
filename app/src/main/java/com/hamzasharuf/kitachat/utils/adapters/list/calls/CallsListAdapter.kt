package com.hamzasharuf.kitachat.utils.adapters.list.calls


import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hamzasharuf.kitachat.data.models.Call
import java.lang.IllegalArgumentException

class CallsListAdapter(private val clickListener: CallsClickListener)
    : ListAdapter<Call, RecyclerView.ViewHolder>(CallsDiffUtil()) {

    companion object{
        const val CALL = 1
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            CALL -> CallViewHolder.from(parent)
            else -> throw IllegalArgumentException("Unknown viewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is CallViewHolder -> holder.bind(getItem(position), clickListener, position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is Call -> CALL
            else -> throw IllegalArgumentException("Unknown Item ${getItem(position)}")
        }
    }
}