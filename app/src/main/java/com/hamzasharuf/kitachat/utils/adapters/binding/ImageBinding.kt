package com.hamzasharuf.kitachat.utils.adapters.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.hamzasharuf.kitachat.R

    /**
     * Load image from the network or cache with placeholder and error images
     */
    @BindingAdapter("loadImage")
    fun loadImage(imageView: ImageView, url: String?) {
        Glide.with(imageView.context)
            .load(url)
            .centerCrop()
            .placeholder(R.drawable.person_placeholder)
            .into(imageView)

    }
