package com.hamzasharuf.kitachat.ui.auth.verification

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContentProviderCompat.requireContext
import com.hamzasharuf.kitachat.R
import kotlinx.android.synthetic.main.fragment_verification.*

class GenericTextWatcher internal constructor(private val currentView: View, private val nextView: View?) :
    TextWatcher {

    override fun afterTextChanged(editable: Editable) { // TODO Auto-generated method stub
        val text = editable.toString()
        when (currentView.id) {
            R.id.etCode1 -> if (text.length == 1) nextView!!.requestFocus()
            R.id.etCode2 -> if (text.length == 1) nextView!!.requestFocus()
            R.id.etCode3 -> if (text.length == 1) nextView!!.requestFocus()
            R.id.etCode4 -> if (text.length == 1) nextView!!.requestFocus()
            R.id.etCode5 -> if (text.length == 1) nextView!!.requestFocus()
            R.id.etCode6 -> if (text.length == 1) {
                val imm = currentView.context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                imm?.hideSoftInputFromWindow(currentView.windowToken, 0)
            }
            //You can use EditText4 same as above to hide the keyboard
        }
    }

    override fun beforeTextChanged(
        arg0: CharSequence,
        arg1: Int,
        arg2: Int,
        arg3: Int
    ) { // TODO Auto-generated method stub
    }

    override fun onTextChanged(
        arg0: CharSequence,
        arg1: Int,
        arg2: Int,
        arg3: Int
    ) { // TODO Auto-generated method stub
    }

}