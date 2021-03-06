package com.a706012110039.signup

import Interface.dialoglistener
import android.app.Activity
import android.content.Context
import com.a706012110039.signup.publicuser.Companion.x
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.get
import com.a706012110039.signup.databinding.ActivityProfileBinding
import database.globalvar
import kotlinx.android.synthetic.main.activity_detilsaham.*
import kotlinx.android.synthetic.main.activity_main.*

class profileActivity : AppCompatActivity(), dialoglistener {
    private lateinit var viewBind: ActivityProfileBinding;
    lateinit var sharedPreferences: SharedPreferences

    private val GetResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == Activity.RESULT_OK){   // APLIKASI GALLERY SUKSES MENDAPATKAN IMAGE
            val uri = it.data?.data                 // GET PATH TO IMAGE FROM GALLEY
            viewBind.imageView.setImageURI(uri)  // MENAMPILKAN DI IMAGE VIEW
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(viewBind.root)
        supportActionBar?.hide()
        sharedPreferences = getSharedPreferences("a", Context.MODE_PRIVATE)

        GetIntent()
        Listener()
    }

    override fun onResume() {
        super.onResume()
        setter(0)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        setter(0)
    }

    override fun onPostResume() {
        super.onPostResume()
        setter(0)
    }



    var nomor: Int = 0
    private fun GetIntent(){
        val angka = intent.getIntExtra("angka1", 0)
        nomor = angka
        setter(nomor)
    }

    private fun Listener()
    {
        viewBind.editProfile.setOnClickListener {
            val myIntent = Intent(this, signupActivity::class.java).apply {
                putExtra("position",globalvar.curuser)
            }
            startActivity(myIntent)
        }

        viewBind.imageView.setOnClickListener{
            //itent ke ambil pp gambar
        }

        viewBind.withdraw.setOnClickListener {
            var dialog = withdrawfragment(this)

            dialog.show(supportFragmentManager, "customdialog")

            setter(0)
        }

        viewBind.deposit.setOnClickListener {
            var dialog = topupfragment(this)

            dialog.show(supportFragmentManager, "customdialog")

            setter(0)
        }

        viewBind.toolbar.get(0).setOnClickListener {
            finish()
        }
        viewBind.toolbar.get(1).setOnClickListener {
            finish()
        }

        viewBind.logout.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
            globalvar.curuser = -1
            val myIntent = Intent(this, MainActivity::class.java)
            startActivity(myIntent)
            finish()
        }
    }

    private fun setter(index: Int)
    {
        viewBind.username.text = x.get(globalvar.curuser).nama
        viewBind.balance.text = x.get(globalvar.curuser).money
        viewBind.Address.text = x.get(globalvar.curuser).alamat
        viewBind.creditcard.text = x.get(globalvar.curuser).credit_card
        viewBind.email.text = x.get(globalvar.curuser).email
    }

    override fun ondialogclicked() {
        setter(0)
    }
}