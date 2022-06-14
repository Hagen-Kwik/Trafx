package com.a706012110039.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import com.a706012110039.signup.databinding.FragmenBuysahamBinding
import com.a706012110039.signup.databinding.FragmentSellsahamBinding
import com.a706012110039.signup.publicuser.Companion.x
import database.globalvar
import model.sahamportfolio

class sellsahamfragment:DialogFragment() {
    private lateinit var viewbind: FragmentSellsahamBinding
    private var price:Int = 0
    private lateinit var sahamportfolio: sahamportfolio

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewbind = FragmentSellsahamBinding.inflate(layoutInflater)
        setup()
        listener()
        return viewbind.root
    }
    private fun setup() {
        val saham  = globalvar.listSaham.get(globalvar.cursaham)
        viewbind.symbol.text = saham.symbol
        viewbind.stock.text
        viewbind.companyName.text = saham.companyname
        viewbind.price.text = saham.open.toString()
        viewbind.totalprice.text = price.toString()
        viewbind.userbalance.text = x.get(globalvar.curuser).money
    }

    private fun listener() {
        viewbind.textInputLayout.editText?.addTextChangedListener {
            var saham  = globalvar.listSaham.get(globalvar.cursaham)

            price = viewbind.textInputLayout.editText?.text.toString().toInt() * saham.open.toString().toInt()
            viewbind.totalprice.text = price.toString()
        }

        viewbind.buysaham.setOnClickListener {

            if(price > x.get(globalvar.curuser).money!!.toInt() ){
                Toast.makeText(activity, "Buystock Failed (insuffiecient money)", Toast.LENGTH_LONG).show()
                dismiss()
            }else{
                var saham  = globalvar.listSaham.get(globalvar.cursaham)

                x.get(globalvar.curuser).money = (x.get(globalvar.curuser).money!!.toInt() - viewbind.textInputLayout.editText?.text.toString().toInt()).toString()
                val temp = sahamportfolio(viewbind.textInputLayout.editText?.text.toString().toInt(), saham.open.toString().toInt())
                temp.addParent(globalvar.listSaham.get(globalvar.cursaham))

                x.get(globalvar.curuser).ownedstock.add(temp)
                Toast.makeText(activity, "Buystock successful", Toast.LENGTH_LONG).show()
                dismiss()
            }
        }
    }
}