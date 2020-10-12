package com.hamzasharuf.kitachat.ui.home

import android.os.Bundle
import android.view.View
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.hamzasharuf.kitachat.R
import com.hamzasharuf.kitachat.databinding.FragmentHomeBinding
import com.hamzasharuf.kitachat.ui.base.BaseFragment
import com.hamzasharuf.kitachat.utils.adapters.HomePagerAdapter
import com.hamzasharuf.kitachat.utils.adapters.HomeSection
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupPager()
        toolbarHome.title = getString(R.string.app_name)
    }

    private fun setupPager() {
        viewPagerHome.adapter = HomePagerAdapter(childFragmentManager, lifecycle)
        TabLayoutMediator(tabLayoutHome, viewPagerHome) { tab, position ->
            tab.text = HomeSection.getItem(position).name
            viewPagerHome.setCurrentItem(tab.position, true)
        }.attach()
        tabLayoutHome.tabGravity = TabLayout.GRAVITY_FILL
    }

    override fun getLayoutRes(): Int = R.layout.fragment_home

}
