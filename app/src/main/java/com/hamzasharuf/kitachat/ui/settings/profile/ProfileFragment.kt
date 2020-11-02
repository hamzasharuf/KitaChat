package com.hamzasharuf.kitachat.ui.settings.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.hamzasharuf.kitachat.R
import com.hamzasharuf.kitachat.databinding.FragmentProfileBinding
import com.hamzasharuf.kitachat.ui.MainSharedViewModel
import com.hamzasharuf.kitachat.ui.base.BaseFragment
import com.hamzasharuf.kitachat.utils.common.uriToBitmap
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class ProfileFragment : BaseFragment<ProfileViewModel, FragmentProfileBinding>(),
    PickPictureBottomSheet.OnImageReadyListener {

    private val viewModel: ProfileViewModel by viewModels()

    private val sharedViewModel: MainSharedViewModel by activityViewModels()

    private lateinit var bottomSheet: PickPictureBottomSheet

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupClickListeners()
    }

    private fun setupClickListeners() {
        fab_camera.setOnClickListener {
            bottomSheet.showDialog()
        }
    }

    private fun setupUI() {
        tv_username.text = sharedViewModel.cachedUser?.userName
        tv_phone.text = sharedViewModel.cachedUser?.userPhone
        tv_about.text = sharedViewModel.cachedUser?.status

        bottomSheet = PickPictureBottomSheet(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (!this::bottomSheet.isInitialized)
            bottomSheet = PickPictureBottomSheet(this)
        bottomSheet.registerOnImageReadyListener(this)
        bottomSheet.registerForActivityResult(requestCode, resultCode, data)
    }

    override fun getLayoutRes() = R.layout.fragment_profile

    /**
     * Part of OnImageReadyListener
     * Runs when the image is received from the gallery
     */
    override fun onImageReady(uri: Uri) {
        val bitmap = uriToBitmap(requireContext(), uri)
        image_profile.setImageBitmap(bitmap)
    }

}