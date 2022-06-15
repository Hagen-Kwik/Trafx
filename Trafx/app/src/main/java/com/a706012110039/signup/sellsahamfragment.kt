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
    private var sellingstock:Int = 0
    private lateinit var sahamportfolio: sahamportfolio
    private var stock:Int = 0

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
        for(i in 0.. x.get(globalvar.curuser).ownedstock.size-1){
            if(x.get(globalvar.curuser).ownedstock.get(i).symbol == globalvar.listSaham.get(globalvar.cursaham).symbol){
                stock += x.get(globalvar.curuser).ownedstock.get(i).qty
            }
        }


        val saham  = globalvar.listSaham.get(globalvar.cursaham)
        viewbind.symbol.text = saham.symbol
        viewbind.stock.text = stock.toString()
        viewbind.companyName.text = saham.companyname
        viewbind.price.text = saham.open.toString()
        viewbind.userbalance.text = x.get(globalvar.curuser).money
        viewbind.stock.text
    }

    private fun listener() {
        viewbind.textInputLayout.editText?.addTextChangedListener {
            var saham  = globalvar.listSaham.get(globalvar.cursaham)

            sellingstock = viewbind.textInputLayout.editText?.text.toString().toInt() * saham.open.toString().toInt()
            viewbind.totalprice.text = sellingstock.toString()
        }

        viewbind.sellsaham.setOnClickListener {
            if(viewbind.textInputLayout.editText?.text.toString().toInt() > stock ){
                Toast.makeText(activity, "Sellstock Failed (insuffiecient stock)", Toast.LENGTH_LONG).show()
                dismiss()
            }else{
                var boughtstock = viewbind.textInputLayout.editText?.text.toString().toInt()
                x.get(globalvar.curuser).money = (x.get(globalvar.curuser).money.toString().toInt() + viewbind.totalprice.text.toString().toInt()).toString()
                for(i in 0.. x.get(globalvar.curuser).ownedstock.size-1){
                    if(x.get(globalvar.curuser).ownedstock.get(i).symbol == globalvar.listSaham.get(globalvar.cursaham).symbol){
                        if(x.get(globalvar.curuser).ownedstock.get(i).qty <= boughtstock){
                            boughtstock -= x.get(globalvar.curuser).ownedstock.get(i).qty
                            x.get(globalvar.curuser).ownedstock.removeAt(i)
                        }else{
                            x.get(globalvar.curuser).ownedstock.get(i).qty -= boughtstock
                            boughtstock = 0
                        }
                        if(boughtstock == 0) {
                            break
                        }
                    }
                }
                dismiss()
            }
        }
    }
}