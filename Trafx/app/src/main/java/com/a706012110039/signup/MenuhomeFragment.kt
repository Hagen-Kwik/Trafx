package com.a706012110039.signup

import Interface.cardlistener
import adaptor.recyclerviewwatchlistAdapter
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.recreate
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.a706012110039.signup.databinding.FragmentMenuhomeBinding
import com.a706012110039.signup.publicuser.Companion.x
import database.globalvar
import model.sahamwatchlist
import kotlin.math.roundToInt
import kotlin.random.Random

class MenuhomeFragment : Fragment(), cardlistener {
    private lateinit var viewbind: FragmentMenuhomeBinding
    private var listSahamwatch: ArrayList<sahamwatchlist> = ArrayList()
    private val adapter = recyclerviewwatchlistAdapter(listSahamwatch, this)
    private var watchlist: ArrayList<Int> = ArrayList()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        viewbind = FragmentMenuhomeBinding.inflate(layoutInflater)
        setupRecyclerView()
        listener()
        var stockownedprice: Int = 0

        for(i in 0..x.get(globalvar.curuser).ownedstock.size-1){
            stockownedprice += x.get(globalvar.curuser).ownedstock.get(i).qty * x.get(globalvar.curuser).ownedstock.get(i).close.toString().toInt()
        }
        viewbind.textView8.text = "Rp." + stockownedprice.toString()
        var i = 0
        while(i <= 4){
            var x = Random.nextInt(5)
            if(!watchlist.contains(x)){
                watchlist.add(x)
                i++
            }
        }
        setuptrending()
        return viewbind.root
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }

    fun setuptrending(){
        var sda = globalvar.listSaham.get(watchlist.get(0)).closeAsli?.minus(globalvar.listSaham.get(watchlist.get(0)).openasli!!)
        var temppp = globalvar.listSaham.get(watchlist.get(0)).closeAsli?.let { sda?.div(it) }
        var multiply = (temppp?.times(100)?.roundToInt() ?:0) / 100.0
        var temppstring = "$multiply%"
        viewbind.item1percent.text = temppstring
        if(temppstring.contains("-")){
            viewbind.item1percent.setTextColor(Color.parseColor("#FFbf1f1f"))
        }
        viewbind.item1price.text = globalvar.listSaham.get(watchlist.get(0)).close.toString()
        viewbind.item1title.text = globalvar.listSaham.get(watchlist.get(0)).symbol.toString()

        sda = globalvar.listSaham.get(watchlist.get(1)).closeAsli?.minus(globalvar.listSaham.get(watchlist.get(1)).openasli!!)
        temppp = globalvar.listSaham.get(watchlist.get(1)).closeAsli?.let { sda?.div(it) }
        multiply = (temppp?.times(100)?.roundToInt() ?:0) / 100.0
        temppstring = "$multiply%"
        viewbind.item2percent.text = temppstring
        if(temppstring.contains("-")){
            viewbind.item2percent.setTextColor(Color.parseColor("#FFbf1f1f"))
        }
        viewbind.item2price.text = globalvar.listSaham.get(watchlist.get(1)).close.toString()
        viewbind.item2title.text = globalvar.listSaham.get(watchlist.get(1)).symbol.toString()

        sda = globalvar.listSaham.get(watchlist.get(2)).closeAsli?.minus(globalvar.listSaham.get(watchlist.get(2)).openasli!!)
        temppp = globalvar.listSaham.get(watchlist.get(2)).closeAsli?.let { sda?.div(it) }
        multiply = (temppp?.times(100)?.roundToInt() ?:0) / 100.0
        temppstring = "$multiply%"
        viewbind.item3percent.text = temppstring
        if(temppstring.contains("-")){
            viewbind.item3percent.setTextColor(Color.parseColor("#FFbf1f1f"))
        }
        viewbind.item3price.text = globalvar.listSaham.get(watchlist.get(2)).close.toString()
        viewbind.item3title.text = globalvar.listSaham.get(watchlist.get(2)).symbol.toString()

        sda = globalvar.listSaham.get(watchlist.get(3)).closeAsli?.minus(globalvar.listSaham.get(watchlist.get(3)).openasli!!)
        temppp = globalvar.listSaham.get(watchlist.get(3)).closeAsli?.let { sda?.div(it) }
        multiply = (temppp?.times(100)?.roundToInt() ?:0) / 100.0
        temppstring = "$multiply%"
        viewbind.item4percent.text = temppstring
        if(temppstring.contains("-")){
            viewbind.item4percent.setTextColor(Color.parseColor("#FFbf1f1f"))
        }
        viewbind.item4price.text = globalvar.listSaham.get(watchlist.get(3)).close.toString()
        viewbind.item4title.text = globalvar.listSaham.get(watchlist.get(3)).symbol.toString()

        sda = globalvar.listSaham.get(watchlist.get(4)).closeAsli?.minus(globalvar.listSaham.get(watchlist.get(4)).openasli!!)
        temppp = globalvar.listSaham.get(watchlist.get(4)).closeAsli?.let { sda?.div(it) }
        multiply = (temppp?.times(100)?.roundToInt() ?:0) / 100.0
        temppstring = "$multiply%"
        viewbind.item5percent.text = temppstring
        if(temppstring.contains("-")){
            viewbind.item5percent.setTextColor(Color.parseColor("#FFbf1f1f"))
        }
        viewbind.item5price.text = globalvar.listSaham.get(watchlist.get(4)).close.toString()
        viewbind.item5title.text = globalvar.listSaham.get(watchlist.get(4)).symbol.toString()
    }

    fun listener(){
        viewbind.imageView6.setOnClickListener{
            val myintent = Intent(activity, profileActivity::class.java)
            startActivity(myintent)
        }
    }

    fun setupRecyclerView(){
        inputdata()
        val layoutManager = LinearLayoutManager(requireActivity().baseContext)
        viewbind.recyclerviewwatchlist.layoutManager = layoutManager
        viewbind.recyclerviewwatchlist.adapter = adapter
    }

    fun inputdata(){
        for (j in 0..x.get(globalvar.curuser).watchlist.size-1){
            for (i in 0..globalvar.listSaham.size-1){
                if(x.get(globalvar.curuser).watchlist.get(j) == i){
                    listSahamwatch.add(sahamwatchlist(globalvar.listSaham.get(i).open,globalvar.listSaham.get(i).high,globalvar.listSaham.get(i).low,globalvar.listSaham.get(i).close,globalvar.listSaham.get(i).volume,globalvar.listSaham.get(i).symbol,globalvar.listSaham.get(i).companyname,globalvar.listSaham.get(i).lastupdate,j)
                    )
                }
            }
        }
    }

    override fun onCardClick(position: Int) {
        var index = x.get(globalvar.curuser).watchlist.get(position)
        globalvar.cursaham = index
        val myIntent = Intent(activity, detilsaham::class.java).apply {
            putExtra("position",index)
        }
        startActivity(myIntent)
    }
}