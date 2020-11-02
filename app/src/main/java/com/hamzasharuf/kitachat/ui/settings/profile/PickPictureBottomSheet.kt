package com.hamzasharuf.kitachat.ui.settings.profile

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.hamzasharuf.kitachat.R
import com.hamzasharuf.kitachat.databinding.BottomSheetPickProfilePictureMethodBinding
import com.hamzasharuf.kitachat.utils.common.AppConstants.TAG
import com.hamzasharuf.kitachat.utils.extensions.isNotNull

class PickPictureBottomSheet(
    private val fragment: Fragment,
) {

    companion object {
        private const val INTENT_IMAGE_TYPE = "image/"
        private const val INTENT_IMAGE_REQUEST_CODE = 5
    }

    lateinit var onImageReadyListener: OnImageReadyListener

    private lateinit var bottomSheet: BottomSheetDialog
    private lateinit var binding: BottomSheetPickProfilePictureMethodBinding

    @SuppressLint("InflateParams")
    fun showDialog() {
        /** Define a new dialog */
        bottomSheet = BottomSheetDialog(fragment.requireContext())

        /** attach to data binding object*/
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(fragment.requireContext()),
            R.layout.bottom_sheet_pick_profile_picture_method,
            null,
            false
        )

        /** Inflate the bottom sheet */
        bottomSheet.setContentView(binding.root)

        /** Show the dialog to the user */
        bottomSheet.show()

        /** Setup click listeners */
        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.lnGallery.setOnClickListener {
            bottomSheet.dismiss()
            bottomSheet.cancel()
            pickPictureFromGallery()
        }
    }

    private fun pickPictureFromGallery() {
        Intent().also {
            it.type = INTENT_IMAGE_TYPE
            it.action = Intent.ACTION_GET_CONTENT
            fragment.startActivityForResult(it, INTENT_IMAGE_REQUEST_CODE)
        }
    }

    fun registerForActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == INTENT_IMAGE_REQUEST_CODE) {
            if (resultCode == RESULT_OK && data.isNotNull()) {
                Log.d(TAG, "registerForActivityResult: Image processed")
                val imageUri = data?.data!!
                if (this::onImageReadyListener.isInitialized)
                    onImageReadyListener.onImageReady(imageUri)
            } else {

            }
        }
    }

    fun registerOnImageReadyListener(onImageReadyListener: OnImageReadyListener) {
        this.onImageReadyListener = onImageReadyListener
    }

    interface OnImageReadyListener {
        fun onImageReady(uri: Uri)
    }

}