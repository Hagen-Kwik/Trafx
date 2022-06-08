package com.a706012110039.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.a706012110039.signup.databinding.FragmentMenumarketBinding

class MenumarketFragment : Fragment() {
    private lateinit var viewbind: FragmentMenumarketBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewbind = FragmentMenumarketBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        listener()
        return viewbind.root
    }

    fun listener(){

    }
}