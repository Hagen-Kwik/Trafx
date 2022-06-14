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
import model.saham
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject


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
        ReadFromDB()

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

    private fun ReadFromDB() {
// to let internet work on main activity and not background
        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val request = JsonObjectRequest(
            com.android.volley.Request.Method.GET,
            "https://www.alphavantage.co/query?function=TIME_SERIES_WEEKLY&symbol=MSFT&apikey=U088XM8LAU3JDVDX"
            ,
            null,
            {
                val jsonObj = it.getJSONObject("Weekly Time Series")
                val jsonObjInner = jsonObj.getJSONObject("2022-06-13")
                val openvalue = jsonObjInner.getString("1. open")
                val closevalue = jsonObjInner.getString("2. high")
                val lowvalue = jsonObjInner.getString("3. low")
                val highvalue = jsonObjInner.getString("4. close")
                val volumevalue = jsonObjInner.getString("5. volume")
                globalvar.listSaham.add(saham(1,2,3,4,5,"PRBW","perusahaangg5","10-123-123"))

            },
            {
                Toast.makeText(getActivity(), "Network Error", Toast.LENGTH_LONG).show()
                it.printStackTrace()
            }
        )

        context?.let { VolleySingleton.getInstance(it).addToRequestQueue(request) }
    }




}