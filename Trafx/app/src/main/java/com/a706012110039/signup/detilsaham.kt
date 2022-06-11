package com.a706012110039.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import androidx.core.view.get
import com.a706012110039.signup.databinding.ActivityDetilsahamBinding
import database.globalvar
import model.saham
import model.user
import com.a706012110039.signup.publicuser.Companion.x
import kotlinx.android.synthetic.main.activity_detilsaham.*

class detilsaham : AppCompatActivity() {
    private lateinit var viewbind: ActivityDetilsahamBinding
    private var bookmarked = false
    private var position:Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        viewbind = ActivityDetilsahamBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(viewbind.root)
        supportActionBar?.hide()
        getbookmark()

        position = globalvar.cursaham
        val saham = globalvar.listSaham[position]
        display(saham)

        listener()
    }

    fun getbookmark(){
        if (x.get(globalvar.curuser).watchlist?.isEmpty() == true){
            viewbind.textView12.text = "+ add bookmark"
            bookmarked = false
        }else{
            if(x.get(globalvar.curuser).watchlist?.contains(globalvar.cursaham) == true){
                viewbind.textView12.text = "- remove bookmark"
                bookmarked = true
            }else{
                viewbind.textView12.text = "+ add bookmark"
                bookmarked = false
            }
        }
    }


    fun listener(){
        viewbind.textView12.setOnClickListener {
            if (bookmarked == false){
                x.get(globalvar.curuser).watchlist?.add(globalvar.cursaham)
                println(x.get(globalvar.curuser).watchlist)
                bookmarked = true
                getbookmark()
            }else{
                x.get(globalvar.curuser).watchlist?.remove(globalvar.cursaham)
                bookmarked = false
                getbookmark()
            }
        }

        viewbind.toolbar.get(1).setOnClickListener {
            finish()
        }

        viewbind.toolbar.get(0).setOnClickListener {
            finish()
        }

        viewbind.buyStock.setOnClickListener {
            openbuydialog()
        }
    }
    fun openbuydialog(){

    }
    fun display(saham: saham){
        viewbind.companyname.text = saham.companyname
        viewbind.symbol.text = saham.symbol
    }
}