package com.hamzasharuf.kitachat.ui.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.hamzasharuf.kitachat.R
import com.hamzasharuf.kitachat.databinding.FragmentHomeBinding
import com.hamzasharuf.kitachat.ui.base.BaseFragment
import com.hamzasharuf.kitachat.utils.adapters.pager.HomePagerAdapter
import com.hamzasharuf.kitachat.utils.adapters.pager.HomeSection
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupPager()
        setupObservers()
        setupToolbar()

    }

    private fun setupObservers() {
        viewModel.pagerState.observe(viewLifecycleOwner, Observer {
            changeFabIcon(it.ordinal)
        })
    }

    private fun setupToolbar() {
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbarHome)
        setHasOptionsMenu(true)
        toolbarHome.title = getString(R.string.app_name)
    }

    private fun setupPager() {
        viewPagerHome.adapter = HomePagerAdapter(childFragmentManager, lifecycle)
        TabLayoutMediator(tabLayoutHome, viewPagerHome) { tab, position ->
            tab.text = HomeSection.getItem(position).name
            viewPagerHome.setCurrentItem(tab.position, true)
        }.attach()

        viewPagerHome.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                viewModel.setPagerState(position)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.chats_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    private fun changeFabIcon(position: Int){
        fab.hide()
        lifecycleScope.launch(Main){
            delay(400)
            when(position){
                0 -> fab.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_message, null))
                1 -> fab.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_camera_lens, null))
                2 -> fab.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_call, null))
            }
            fab.show()
        }
    }

    override fun getLayoutRes(): Int = R.layout.fragment_home

}
