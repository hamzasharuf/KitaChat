package com.hamzasharuf.kitachat.ui.chats

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DefaultItemAnimator
import com.hamzasharuf.kitachat.R
import com.hamzasharuf.kitachat.data.database.DummyDataSource.getChatsList
import com.hamzasharuf.kitachat.databinding.FragmentChatsBinding
import com.hamzasharuf.kitachat.ui.base.BaseFragment
import com.hamzasharuf.kitachat.utils.adapters.list.chats.ChatsClickListener
import com.hamzasharuf.kitachat.utils.adapters.list.chats.ChatsListAdapter
import com.hamzasharuf.kitachat.utils.view.MarginItemDecoration
import com.hamzasharuf.kitachat.utils.view.SpeedyLinearLayoutManager
import kotlinx.android.synthetic.main.fragment_chats.*
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class ChatsFragment : BaseFragment<ChatsViewModel, FragmentChatsBinding>() {

    private lateinit var mAdapter: ChatsListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        val list = getChatsList()
        mAdapter.submitList(list)
        mAdapter.notifyDataSetChanged()

    }

    private fun setupRecyclerView() {
        with(rvChatsRecycler) {
            setupAdapter()
            layoutManager = SpeedyLinearLayoutManager(requireContext())
            adapter = mAdapter
            hasFixedSize()
            addItemDecoration(MarginItemDecoration(32))
            itemAnimator = DefaultItemAnimator()
        }
    }

    private fun setupAdapter() {
        mAdapter = ChatsListAdapter(ChatsClickListener { item, position ->
            Toast.makeText(
                requireContext(),
                "Id = ${item.userId} & position = $position",
                Toast.LENGTH_SHORT
            ).show()
        })
    }

    override fun getLayoutRes(): Int = R.layout.fragment_chats
}
