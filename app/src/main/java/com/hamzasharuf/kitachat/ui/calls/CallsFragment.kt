package com.hamzasharuf.kitachat.ui.calls

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import com.hamzasharuf.kitachat.R
import com.hamzasharuf.kitachat.data.database.DummyDataSource
import com.hamzasharuf.kitachat.databinding.FragmentCallsBinding
import com.hamzasharuf.kitachat.ui.base.BaseFragment
import com.hamzasharuf.kitachat.utils.adapters.list.calls.CallsClickListener
import com.hamzasharuf.kitachat.utils.adapters.list.calls.CallsListAdapter
import com.hamzasharuf.kitachat.utils.view.MarginItemDecoration
import com.hamzasharuf.kitachat.utils.view.SpeedyLinearLayoutManager
import kotlinx.android.synthetic.main.fragment_calls.*

class CallsFragment : BaseFragment<CallsViewModel, FragmentCallsBinding>() {

    lateinit var mAdapter: CallsListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        val list = DummyDataSource.getEmptyCallsList()
        mAdapter.submitList(list)
        mAdapter.notifyDataSetChanged()
    }

    private fun setupRecyclerView() {
        with(rvCallsRecycler) {
            setupAdapter()
            layoutManager = SpeedyLinearLayoutManager(requireContext())
            adapter = mAdapter
            hasFixedSize()
            addItemDecoration(DividerItemDecoration(requireContext(), (layoutManager as SpeedyLinearLayoutManager).orientation))
            itemAnimator = DefaultItemAnimator()
        }
    }

    private fun setupAdapter() {
        mAdapter = CallsListAdapter(CallsClickListener { item, position ->
            Toast.makeText(
                requireContext(),
                "Id = ${item.userId} & position = $position",
                Toast.LENGTH_SHORT
            ).show()
        })
    }

    override fun getLayoutRes() = R.layout.fragment_calls

}
