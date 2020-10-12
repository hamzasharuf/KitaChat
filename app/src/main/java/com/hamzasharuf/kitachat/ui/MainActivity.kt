package com.hamzasharuf.kitachat.ui

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.hamzasharuf.kitachat.R
import com.hamzasharuf.kitachat.utils.common.CommonFunctions.hideStatusBar
import com.hamzasharuf.kitachat.utils.common.CommonFunctions.showStatusBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavController()
    }

    private fun setupNavController() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        navController = navHostFragment.navController

        navController.addOnDestinationChangedListener{
                _, destination, _ ->
            when(destination.id){
                R.id.splashFragment -> hideStatusBar(this)
                else -> showStatusBar(this)
            }
        }
    }
}


