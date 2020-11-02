package com.hamzasharuf.kitachat.utils.common

import android.R
import android.animation.ObjectAnimator
import android.app.Activity
import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat


object CommonFunctions {

    private const val STATUS_BAR_COLOR_PROPERTY_NAME = "statusBarColor"

    @JvmStatic
    fun hideStatusBar(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            activity.window.setDecorFitsSystemWindows(false)
        } else {
            activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        }
    }

    @JvmStatic
    fun changeStatusBarColor(activity: Activity, @ColorRes colorRes: Int) {
        val window: Window = activity.window

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

        // change the color
        // window.statusBarColor = ResourcesCompat.getColor(activity.resources, colorRes, null)

        // change the color (Animation)
        val startColor: Int = window.statusBarColor
        val endColor = ContextCompat.getColor(activity, colorRes)
        ObjectAnimator.ofArgb(window, STATUS_BAR_COLOR_PROPERTY_NAME, startColor, endColor).setDuration(400).start()
    }

    @JvmStatic
    fun showStatusBar(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            activity.window.setDecorFitsSystemWindows(true)
        } else {
            activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
        }
    }
}