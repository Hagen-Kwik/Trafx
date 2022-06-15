package com.a706012110039.signup

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import com.a706012110039.signup.databinding.ActivityDetilsahamBinding
import com.a706012110039.signup.publicuser.Companion.x
import com.androidplot.xy.*
import database.globalvar
import model.saham
import java.text.FieldPosition
import java.text.Format
import java.text.ParsePosition
import java.util.*
import kotlin.math.roundToInt


class detilsaham : AppCompatActivity() {
    private lateinit var viewbind: ActivityDetilsahamBinding
    private var bookmarked = false
    private var position:Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        viewbind = ActivityDetilsahamBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(viewbind.root)
        supportActionBar?.hide()
        getbookmark()

        position = globalvar.cursaham
        val saham = globalvar.listSaham[position]
        display(saham)

        listener()          
        
    }

    fun getbookmark(){
        if (x.get(globalvar.curuser).watchlist?.isEmpty() == true){
            viewbind.textView12.text = "+ add bookmark"
            bookmarked = false
        }else{
            if(x.get(globalvar.curuser).watchlist?.contains(globalvar.cursaham) == true){
                viewbind.textView12.text = "- remove bookmark"
                bookmarked = true
            }else{
                viewbind.textView12.text = "+ add bookmark"
                bookmarked = false
            }
        }
    }


    fun listener(){
        viewbind.textView12.setOnClickListener {
            if (bookmarked == false){
                x.get(globalvar.curuser).watchlist?.add(globalvar.cursaham)
                println(x.get(globalvar.curuser).watchlist)
                bookmarked = true
                getbookmark()
            }else{
                x.get(globalvar.curuser).watchlist?.remove(globalvar.cursaham)
                bookmarked = false
                getbookmark()
            }
        }

        viewbind.sellStock.setOnClickListener {
            var dialog = buysahamfragment()

            dialog.show(supportFragmentManager, "customdialog")
        }

        viewbind.toolbar.get(1).setOnClickListener {
            finish()
        }

        viewbind.toolbar.get(0).setOnClickListener {
            finish()
        }

        viewbind.buyStock.setOnClickListener {
            openbuydialog()
        }

        viewbind.sellStock.setOnClickListener {
            openselldialog()
        }
    }
    fun openbuydialog(){
        var dialog = buysahamfragment()

        dialog.show(supportFragmentManager, "customdialog")
    }

    fun openselldialog(){
        var dialog = sellsahamfragment()

        dialog.show(supportFragmentManager, "customdialog")
    }

    private var plot: XYPlot? = null

    fun display(saham: saham){
        viewbind.companyname.text = saham.companyname
        viewbind.symbol.text = saham.symbol
        viewbind.curhigh.text = saham.high.toString()
        viewbind.curlow.text = saham.low.toString()
        viewbind.curlow2.text = saham.close.toString()
        viewbind.curvolume.text = saham.volume.toString()
        viewbind.value.text = saham.close.toString()
        var sda = saham.closeAsli?.minus(saham.openasli!!)
        var temppp = saham.closeAsli?.let { sda?.div(it) }
        var multiply = (temppp?.times(100)?.roundToInt() ?:0) / 100.0
        var temppstring = "$multiply%"
        viewbind.change.text = temppstring
        if(viewbind.change.text.contains("-")){
            viewbind.change.setTextColor(Color.parseColor("#FFbf1f1f"))
        }else{
            viewbind.change.setTextColor(Color.parseColor("#FF1fbf44"))
        }



        val t = saham.numberforGRAPH
        val ss: Array<Int> = t!!.toTypedArray()
        Log.d("print", t.toString())
//        val series1: XYSeries = new simpleXYSeries(Arrays.asList(*series1Number), SimpleXYSerues.ArrayFormat)

        plot = viewbind.XYPlot


        // turn the above arrays into XYSeries':
        // (Y_VALS_ONLY means use the element index as the x value)
        val series1: XYSeries = SimpleXYSeries(t,SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Series1")


        // create formatters to use for drawing a series using LineAndPointRenderer
        // and configure them from xml:

        // create formatters to use for drawing a series using LineAndPointRenderer
        // and configure them from xml:
        val series1Format = LineAndPointFormatter(Color.RED, Color.GREEN, null, null)


        // add an "dash" effect to the series2 line:

        // add an "dash" effect to the series2 line:


        series1Format.interpolationParams =
            CatmullRomInterpolator.Params(10, CatmullRomInterpolator.Type.Uniform)


        plot!!.addSeries(series1, series1Format)

        
    }
}