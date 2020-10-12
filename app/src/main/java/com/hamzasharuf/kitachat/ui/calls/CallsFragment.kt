package com.hamzasharuf.kitachat.ui.calls

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hamzasharuf.kitachat.R

class CallsFragment : Fragment() {

    companion object {
        fun newInstance() = CallsFragment()
    }

    private lateinit var viewModel: CallsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_calls, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CallsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}