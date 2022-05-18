package com.a706012110039.signup

import android.app.Activity
import com.a706012110039.signup.publicuser.Companion.x
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.a706012110039.signup.databinding.ActivityProfileBinding

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
        GetIntent()
        Listener()
    }

    var nomor: Int = 0
    private fun GetIntent(){
        val angka = intent.getIntExtra("angka1", 0)
        nomor = angka
        setter(nomor)
    }

    private fun Listener()
    {
        viewBind.imageView3.setOnClickListener {
            val myIntent = Intent(this, MainActivity::class.java)
            startActivity(myIntent)
            finish()
        }

        viewBind.imageView.setOnClickListener{
            val myIntent = Intent(Intent.ACTION_PICK)
            myIntent.type = "image/*"
            GetResult.launch(myIntent)
        }

        viewBind.button2.setOnClickListener{
            x.removeAt(nomor.toInt())
            val myIntent = Intent(this, MainActivity::class.java)
            startActivity(myIntent)
            finish()
        }
    }

    private fun setter(index: Int)
    {
        viewBind.textView3.text = x.get(index).nama
        viewBind.textView4.text = x.get(index).email
    }
}