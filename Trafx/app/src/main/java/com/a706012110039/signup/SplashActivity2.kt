package com.a706012110039.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.a706012110039.signup.databinding.ActivitySplash2Binding

import android.animation.*
import android.os.StrictMode
import android.widget.ImageView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import database.VolleySingleton
import database.globalvar
import model.saham
import kotlin.math.round

class SplashActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivitySplash2Binding;
    lateinit var star: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash2)
        supportActionBar?.hide()
        binding = ActivitySplash2Binding.inflate(layoutInflater)
        binding.imageView2.alpha = 0f

        tesjalan()

        star = findViewById(R.id.imageView2)
        star.alpha = 0f
        star.animate().setDuration(3000).alpha(1f).withEndAction{
//            val myintent = Intent(this, MainActivity::class.java)
            val myintent = Intent(this, MainActivity::class.java)
            startActivity(myintent)
            finish()
        }
    }

    fun tesjalan(){
        ReadFromDB("ADTH", "AdTheorent Holding Company")
        ReadFromDB("AMCR", "Amcor PLC")
        ReadFromDB("PBFX", "PBF Logistics LP")
        ReadFromDB("CMRE", "Costamare Inc")
        ReadFromDB("VGR", "Vector Group Ltd")
    }

    private fun ReadFromDB(symbol:String, compname:String) {
// to let internet work on main activity and not background
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        var url1 = "https://www.alphavantage.co/query?function=TIME_SERIES_WEEKLY&symbol="
        var url2 = "&apikey=U088XM8LAU3JDVDX"
        var url = url1 + symbol + url2

        val request = JsonObjectRequest(
            Request.Method.GET,
            url
            ,
            null,
            {
                val jsonObj = it.getJSONObject("Weekly Time Series")
                val jsonObjInner = jsonObj.getJSONObject("2022-08-26")

                //round to int
                val openvalue = round(jsonObjInner.getString("1. open").toDouble()).toInt() *14500
                val closevalue =  round(jsonObjInner.getString("2. high").toDouble()).toInt() *14500
                val lowvalue =  round(jsonObjInner.getString("3. low").toDouble()).toInt() *14500
                val highvalue =  round(jsonObjInner.getString("4. close").toDouble()).toInt() *14500
                val volumevalue =  round(jsonObjInner.getString("5. volume").toDouble()).toInt() *14500
                val openasli = jsonObjInner.getString("1. open").toFloat() * 14500
                val closeasli = jsonObjInner.getString("4. close").toFloat() * 14500

                var dataforGRAPH: MutableList<Int> = arrayListOf()
                var dates= arrayOf("2022-08-26", "2022-08-19", "2022-08-12", "2022-08-05", "2022-07-29", "2022-07-22", "2022-07-15", "2022-07-08", "2022-07-01")
                for (date in dates){
                    dataforGRAPH.add((jsonObj.getJSONObject(date).getString("4. close").toDouble()).toInt()*14500)
                }

                globalvar.listSaham.add(saham(openvalue,highvalue,lowvalue,closevalue,volumevalue,symbol,compname,"26-08-2022",openasli,closeasli,dataforGRAPH))

            },
            {
                Toast.makeText(this, "Network Error", Toast.LENGTH_LONG).show()
                it.printStackTrace()
            }
        )

        this?.let { VolleySingleton.getInstance(it).addToRequestQueue(request) }
    }

}