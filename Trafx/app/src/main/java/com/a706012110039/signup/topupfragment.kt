package com.a706012110039.signup

import Interface.dialoglistener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.a706012110039.signup.databinding.ActivityProfileBinding
import com.a706012110039.signup.databinding.FragmenTopupBinding
import com.a706012110039.signup.publicuser.Companion.x
import database.globalvar

class topupfragment(val xyz : dialoglistener):DialogFragment() {
    private lateinit var viewbind: FragmenTopupBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewbind = FragmenTopupBinding.inflate(layoutInflater)
        listener()
        return viewbind.root
    }

    private fun listener() {
        viewbind.topup.setOnClickListener {
            x.get(globalvar.curuser).money = (x.get(globalvar.curuser).money!!.toInt() + viewbind.textInputLayout.editText?.text.toString().toInt()).toString()
            Toast.makeText(activity, "Topup successful", Toast.LENGTH_LONG).show()
            xyz.ondialogclicked()
            dismiss()
        }
    }
}