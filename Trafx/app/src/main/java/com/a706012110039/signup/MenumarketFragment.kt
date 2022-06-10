package com.a706012110039.signup

import adaptor.recyclerviewsahamAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.a706012110039.signup.databinding.ActivityBottomnavbarBinding
import com.a706012110039.signup.databinding.FragmentMenumarketBinding
import model.saham

class MenumarketFragment : Fragment() {
    private lateinit var viewbind: FragmentMenumarketBinding
    private val listsahammarket = ArrayList<saham>()
    private val adapter = recyclerviewsahamAdapter(listsahammarket)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewbind = FragmentMenumarketBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        listener()
        setupRecyclerView()
        tesjalan()
        return viewbind.root
    }

    fun setupRecyclerView(){
        val layoutManager = LinearLayoutManager(requireActivity().baseContext)
        viewbind.recyclerviewsaham.layoutManager = layoutManager
        viewbind.recyclerviewsaham.adapter = adapter
    }

    fun tesjalan(){
        listsahammarket.add(saham(1,2,3,4,5,"ASDF","perusahaangg1","10-123-123"))
        listsahammarket.add(saham(1,2,3,4,5,"ASDF","perusahaangg1","10-123-123"))
        listsahammarket.add(saham(1,2,3,4,5,"ASDF","perusahaangg1","10-123-123"))
        listsahammarket.add(saham(1,2,3,4,5,"ASDF","perusahaangg1","10-123-123"))
        listsahammarket.add(saham(1,2,3,4,5,"ASDF","perusahaangg1","10-123-123"))

        adapter.notifyDataSetChanged()
    }

    fun listener(){

    }
}