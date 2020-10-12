package com.hamzasharuf.kitachat.ui.chats

import com.hamzasharuf.kitachat.R
import com.hamzasharuf.kitachat.databinding.FragmentChatsBinding
import com.hamzasharuf.kitachat.ui.base.BaseFragment

class ChatsFragment : BaseFragment<ChatsViewModel, FragmentChatsBinding>() {


    override fun getLayoutRes(): Int = R.layout.fragment_chats
}
