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
        ReadFromDB("ADTH", "AdTheorent Holding Company")
        ReadFromDB("PBFX", "PBF Logistics LP")
        ReadFromDB("CMRE", "Costamare Inc")
        ReadFromDB("VGR", "Vector Group Ltd")
        ReadFromDB("AMCR", "Amcor PLC")

        adapter.notifyDataSetChanged()
    }

    override fun onCardClick(position: Int) {
        globalvar.cursaham = position
        val myIntent = Intent(activity, detilsaham::class.java).apply {
            putExtra("position",position)
        }
        startActivity(myIntent)
    }

    private fun ReadFromDB(symbol:String, compname:String) {
// to let internet work on main activity and not background
        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        var url1 = "https://www.alphavantage.co/query?function=TIME_SERIES_WEEKLY&symbol="
        var url2 = "&apikey=U088XM8LAU3JDVDX"
        var url = url1 + symbol + url2

        val request = JsonObjectRequest(
            com.android.volley.Request.Method.GET,
            url
            ,
            null,
            {
                val jsonObj = it.getJSONObject("Weekly Time Series")
                val jsonObjInner = jsonObj.getJSONObject("2022-06-13")
                //round to int
                val openvalue = round(jsonObjInner.getString("1. open").toDouble()).toInt() *14500
                val closevalue =  round(jsonObjInner.getString("2. high").toDouble()).toInt() *14500
                val lowvalue =  round(jsonObjInner.getString("3. low").toDouble()).toInt() *14500
                val highvalue =  round(jsonObjInner.getString("4. close").toDouble()).toInt() *14500
                val volumevalue =  round(jsonObjInner.getString("5. volume").toDouble()).toInt() *14500
                val openasli = jsonObjInner.getString("1. open").toFloat() * 14500
                val closeasli = jsonObjInner.getString("4. close").toFloat() * 14500

                globalvar.listSaham.add(saham(openvalue,highvalue,lowvalue,closevalue,volumevalue,symbol,compname,"14-06-2022",openasli,closeasli))

            },
            {
                Toast.makeText(getActivity(), "Network Error", Toast.LENGTH_LONG).show()
                it.printStackTrace()
            }
        )

        context?.let { VolleySingleton.getInstance(it).addToRequestQueue(request) }
    }




}