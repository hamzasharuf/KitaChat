package com.hamzasharuf.kitachat.utils.adapters.binding

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.hamzasharuf.kitachat.R
import com.hamzasharuf.kitachat.utils.adapters.list.calls.CallType

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

@BindingAdapter("callType")
fun loadCallTypeDrawable(imageView: ImageView, type: CallType) {
    @DrawableRes val res = when(type){
        CallType.INCOMING -> R.drawable.ic_arrow_upward
        CallType.OUTGOING -> R.drawable.ic_arrow_downward
    }
    Glide.with(imageView.context)
        .load(res)
        .centerCrop()
        .placeholder(R.drawable.person_placeholder)
        .into(imageView)
}

