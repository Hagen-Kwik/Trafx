package com.a706012110039.signup

import android.app.Activity
import com.a706012110039.signup.publicuser.Companion.x
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.get
import com.a706012110039.signup.databinding.ActivityProfileBinding
import database.globalvar
import kotlinx.android.synthetic.main.activity_detilsaham.*
import kotlinx.android.synthetic.main.activity_main.*

class profileActivity : AppCompatActivity() {
    private lateinit var viewBind: ActivityProfileBinding;

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

    override fun onTopResumedActivityChanged(isTopResumedActivity: Boolean) {
        super.onTopResumedActivityChanged(isTopResumedActivity)
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
            var dialog = withdrawfragment()

            dialog.show(supportFragmentManager, "customdialog")

            setter(0)
        }

        viewBind.deposit.setOnClickListener {
            var dialog = topupfragment()

            dialog.show(supportFragmentManager, "customdialog")

            setter(0)
        }

        viewBind.toolbar.get(0).setOnClickListener {
            finish()
        }
        viewBind.toolbar.get(1).setOnClickListener {
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
}