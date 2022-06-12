package com.a706012110039.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.a706012110039.signup.databinding.ActivityProfileBinding
import com.a706012110039.signup.databinding.FragmenWithdrawBinding
import com.a706012110039.signup.publicuser.Companion.x
import database.globalvar

class withdrawfragment:DialogFragment() {
    private lateinit var viewbind: FragmenWithdrawBinding
    private lateinit var bind: ActivityProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewbind = FragmenWithdrawBinding.inflate(layoutInflater)
        bind = ActivityProfileBinding.inflate(layoutInflater)


        viewbind.textView15.text = x.get(globalvar.curuser).money
        listener()
        return viewbind.root
    }

    private fun listener() {
        viewbind.withdraw.setOnClickListener {
            if(viewbind.textInputLayout.editText?.text.toString().toInt() > x.get(globalvar.curuser).money!!.toInt() ){
                Toast.makeText(activity, "Withdraw Failed (insuffiecient money)", Toast.LENGTH_LONG).show()
                dismiss()
            }else{
                x.get(globalvar.curuser).money = (x.get(globalvar.curuser).money!!.toInt() - viewbind.textInputLayout.editText?.text.toString().toInt()).toString()
                Toast.makeText(activity, "Withdraw successful", Toast.LENGTH_LONG).show()
                bind.balance.text = x.get(globalvar.curuser).money
                dismiss()
            }
        }
    }
}