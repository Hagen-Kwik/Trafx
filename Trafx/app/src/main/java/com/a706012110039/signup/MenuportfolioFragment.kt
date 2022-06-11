package com.a706012110039.signup

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.a706012110039.signup.databinding.FragmentMenuportfolioBinding
import model.saham
import model.sahamportfolio


class MenuportfolioFragment : Fragment() {
    private lateinit var viewbind: FragmentMenuportfolioBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewbind = FragmentMenuportfolioBinding.inflate(layoutInflater)
        listener()
        // Inflate the layout for this fragment
        return viewbind.root

    }

    private fun listener() {
        viewbind.imageView6.setOnClickListener{
            val myintent = Intent(activity, profileActivity::class.java)
            startActivity(myintent)
        }
    }


}