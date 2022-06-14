package com.a706012110039.signup

import Interface.cardlistener
import adaptor.recyclerviewportfolioAdapter
import adaptor.recyclerviewsahamAdapter
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.a706012110039.signup.databinding.FragmentMenuportfolioBinding
import com.a706012110039.signup.publicuser.Companion.x
import database.globalvar
import model.saham
import model.sahamportfolio


class MenuportfolioFragment : Fragment(),cardlistener {
    private lateinit var viewbind: FragmentMenuportfolioBinding
    private lateinit var adapter: recyclerviewportfolioAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewbind = FragmentMenuportfolioBinding.inflate(layoutInflater)
        adapter = recyclerviewportfolioAdapter(x.get(globalvar.curuser).ownedstock, this)
        listener()
        // Inflate the layout for this fragment

        //code

        setupRecyclerView()

        return viewbind.root

    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(requireActivity().baseContext)
        viewbind.recyclerviewportfolio.layoutManager = layoutManager
        viewbind.recyclerviewportfolio.adapter = adapter
    }

    private fun listener() {
        viewbind.imageView6.setOnClickListener{
            val myintent = Intent(activity, profileActivity::class.java)
            startActivity(myintent)
        }
    }

    override fun onCardClick(position: Int) {
       var symbol =  x.get(globalvar.curuser).ownedstock.get(position).symbol
        for (i in 0..globalvar.listSaham.size){
            if(globalvar.listSaham.get(i).symbol == symbol){
                globalvar.cursaham = i
                break
            }
        }

        val myIntent = Intent(activity, detilsaham::class.java).apply {
            putExtra("position",position)
        }
        startActivity(myIntent)
    }


}