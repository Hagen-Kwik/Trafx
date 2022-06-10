package com.a706012110039.signup

import android.app.ActionBar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.a706012110039.signup.databinding.FragmentMenuhomeBinding

class MenuhomeFragment : Fragment() {
    private lateinit var viewbind: FragmentMenuhomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewbind = FragmentMenuhomeBinding.inflate(layoutInflater)

        return viewbind.root
    }
}