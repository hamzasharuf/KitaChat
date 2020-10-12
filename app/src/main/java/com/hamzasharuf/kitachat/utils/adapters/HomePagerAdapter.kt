package com.hamzasharuf.kitachat.utils.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.hamzasharuf.kitachat.ui.calls.CallsFragment
import com.hamzasharuf.kitachat.ui.chats.ChatsFragment
import com.hamzasharuf.kitachat.ui.status.StatusFragment
import java.lang.IllegalStateException

class HomePagerAdapter (fm: FragmentManager, lifecycle: Lifecycle)
    : FragmentStateAdapter(fm, lifecycle) {

    override fun getItemCount(): Int = HomeSection.values().size

    override fun createFragment(position: Int): Fragment = when(position){
        0 -> ChatsFragment()
        1 -> StatusFragment()
        2 -> CallsFragment()
        else -> throw IllegalStateException("Unknown position: $position")
    }

}