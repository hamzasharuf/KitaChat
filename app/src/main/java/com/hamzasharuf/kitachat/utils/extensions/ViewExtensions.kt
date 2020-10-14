package com.hamzasharuf.kitachat.utils.extensions

import android.view.View
import android.view.ViewTreeObserver

fun View.setVisible() {
    this.visibility = View.VISIBLE
}

fun View.setInvisible() {
    this.visibility = View.INVISIBLE
}

fun View.setGone() {
    this.visibility = View.GONE
}

inline fun View.waitForLayout(crossinline f: () -> Unit) = with(viewTreeObserver) {
    addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            f()
            removeOnGlobalLayoutListener(this)
        }
    })
}