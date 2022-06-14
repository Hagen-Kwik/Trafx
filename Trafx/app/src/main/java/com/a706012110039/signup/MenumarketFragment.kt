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
import database.globalvar
import kotlinx.android.synthetic.main.fragment_menumarket.*
import model.saham
import okhttp3.OkHttpClient
import okhttp3.Request



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
        setupapi()

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
        // to let internet work on main activity and not background
        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val client = OkHttpClient()
        client.callTimeoutMillis

        val request = Request.Builder()
            .url("https://alpha-vantage.p.rapidapi.com/query?function=TIME_SERIES_WEEKLY&symbol=MSFT&datatype=json")
            .get()
            .addHeader("X-RapidAPI-Key", "77e5627b95mshf7386df80e08914p186cc6jsn7cd6cb9c8eca")
            .addHeader("X-RapidAPI-Host", "alpha-vantage.p.rapidapi.com")
            .build()

        val response = client.newCall(request).execute()

        val t = response.body?.string()

        if (t != null) {
            Log.println(Log.DEBUG,"debug", t)
        };


        val volumeindex = t?.indexOf("volume")
        val khususvlomueindex = t?.indexOf("\"},",230)
        val highindex = t?.indexOf("high\":")
        val lowindex = t?.indexOf("low\":")
        val openindex = t?.indexOf("open\":")
        val closeindex = t?.indexOf("close\":")

        //volumenya berbeda"
        val volumevalue = t?.subSequence(volumeindex?.plus(10) ?: 0,
            volumeindex?.plus(18) ?: 0).toString().toLong()
        //belum gnati to long code di atas

        val highvalue = 14723 * t?.subSequence(highindex?.plus(8) ?: 0,
            highindex?.plus(11) ?: 0).toString().toInt()

        if (t != null) {
            Log.println(Log.DEBUG,"try", volumevalue.toString())
            Log.println(Log.DEBUG,"angkanya", t?.subSequence(khususvlomueindex?.minus(-40) ?: 0,khususvlomueindex?.plus(50) ?: 0) as String)
        };

//
//        val lowvalue = 14723 * t?.subSequence(lowindex?.plus(7) ?: 0,
//            lowindex?.plus(10) ?: 0).toString().toInt()
//
//        val openvalue = 14723 * t?.subSequence(openindex?.plus(8) ?: 0,
//            openindex?.plus(11) ?: 0).toString().toInt()
//
//        val closevalue = 14723 * t?.subSequence(closeindex?.plus(9) ?: 0,
//            closeindex?.plus(12) ?: 0).toString().toInt()

        viewbind.TRIAL.text = volumevalue.toString()

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