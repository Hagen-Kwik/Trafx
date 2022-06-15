package com.a706012110039.signup

import Interface.cardlistener
import adaptor.recyclerviewsahamAdapter
import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.a706012110039.signup.databinding.FragmentMenumarketBinding
import com.android.volley.toolbox.JsonObjectRequest
import database.VolleySingleton
import database.globalvar
import kotlinx.android.synthetic.main.fragment_menumarket.*
import kotlinx.coroutines.delay
import model.saham
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.internal.wait
import org.json.JSONObject
import kotlin.math.round


class MenumarketFragment : Fragment(),cardlistener {
    private lateinit var viewbind: FragmentMenumarketBinding
    private val listsahammarket = ArrayList<saham>()
    private val adapter = recyclerviewsahamAdapter(globalvar.listSaham, this)



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        viewbind = FragmentMenumarketBinding.inflate(layoutInflater, container, false)
//        val SDK_INT = Build.VERSION.SDK_INT
//        if (SDK_INT > 8) {
//            val policy = ThreadPolicy.Builder()
//                .permitAll().build()
//            StrictMode.setThreadPolicy(policy)
//            //your codes here
//        }
        // Inflate the layout for this fragment

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
        val layoutManager = LinearLayoutManager(requireActivity().baseContext)
        viewbind.recyclerviewsaham.layoutManager = layoutManager
        viewbind.recyclerviewsaham.adapter = adapter
    }



    override fun onCardClick(position: Int) {
        globalvar.cursaham = position
        val myIntent = Intent(activity, detilsaham::class.java).apply {
            putExtra("position",position)
        }
        startActivity(myIntent)
    }






}