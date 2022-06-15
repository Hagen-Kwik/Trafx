package com.a706012110039.signup

import Interface.cardlistener
import adaptor.recyclerviewwatchlistAdapter
import android.content.Context
import android.content.Intent
import android.os.Bundle
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

class MenuhomeFragment : Fragment(), cardlistener {
    private lateinit var viewbind: FragmentMenuhomeBinding
    private var listSahamwatch: ArrayList<sahamwatchlist> = ArrayList()
    private val adapter = recyclerviewwatchlistAdapter(listSahamwatch, this)



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        viewbind = FragmentMenuhomeBinding.inflate(layoutInflater)
        setupRecyclerView()
        listener()

        return viewbind.root

    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()

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