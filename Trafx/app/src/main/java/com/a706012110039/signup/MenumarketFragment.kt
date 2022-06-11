package com.a706012110039.signup

import Interface.cardlistener
import adaptor.recyclerviewsahamAdapter
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.a706012110039.signup.databinding.FragmentMenumarketBinding
import database.globalvar
import model.saham
import okhttp3.OkHttpClient
import okhttp3.Request


class MenumarketFragment : Fragment(),cardlistener {
    private lateinit var viewbind: FragmentMenumarketBinding
    private val listsahammarket = ArrayList<saham>()
    private val adapter = recyclerviewsahamAdapter(globalvar.listSaham, this)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
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
        //setupapi()
        setupRecyclerView()
        listener()
        if(globalvar.repeat == 0){
            tesjalan()
            globalvar.repeat++
        }

        return viewbind.root
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }

    fun setupapi(){

        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://alpha-vantage.p.rapidapi.com/query?function=TIME_SERIES_WEEKLY&symbol=MSFT&datatype=json")
            .get()
            .addHeader("X-RapidAPI-Key", "77e5627b95mshf7386df80e08914p186cc6jsn7cd6cb9c8eca")
            .addHeader("X-RapidAPI-Host", "alpha-vantage.p.rapidapi.com")
            .build()

        val response = client.newCall(request).execute()
        println(response.body.toString())

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

    fun tesjalan(){
        globalvar.listSaham.add(saham(1,2,3,4,5,"ASDF","perusahaangg1","10-123-123"))
        globalvar.listSaham.add(saham(1,2,3,4,5,"KDRT","perusahaangg2","10-123-123"))
        globalvar.listSaham.add(saham(1,2,3,4,5,"PKI","perusahaangg3","10-123-123"))
        globalvar.listSaham.add(saham(1,2,3,4,5,"G30","perusahaangg4","10-123-123"))
        globalvar.listSaham.add(saham(1,2,3,4,5,"PRBW","perusahaangg5","10-123-123"))

        adapter.notifyDataSetChanged()
    }

    override fun onCardClick(position: Int) {
        globalvar.cursaham = position
        val myIntent = Intent(activity, detilsaham::class.java).apply {
            putExtra("position",position)
        }
        startActivity(myIntent)
    }
}