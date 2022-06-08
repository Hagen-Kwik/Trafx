package com.a706012110039.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.a706012110039.signup.publicuser.Companion.x
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import model.user
import com.a706012110039.signup.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewBind: ActivityMainBinding;
    private lateinit var user:user


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBind.root)
        supportActionBar?.hide()
        viewBind.button.isEnabled = false
        listener()


//        example: textview2 langsung aku panggil tanpa harus ada binding
// okeyy tyyyyy        textView2.setText("adsfsda")

    }
    var pswCompleted = false
    var nameCompleted = false
    var index:Int = 0
    private fun listener()
    {
        viewBind.signup.setOnClickListener{
            val myIntent = Intent(this, signupActivity::class.java)
            startActivity(myIntent)
        }

        viewBind.PasswordTextInputLayout.editText?.addTextChangedListener {
            var password = viewBind.PasswordTextInputLayout.editText?.text.toString().trim()
            if (password.isEmpty()){
                viewBind.PasswordTextInputLayout.error = "Tolong isi kolom password"
                pswCompleted = false
                benar()
            }else
            {
                viewBind.PasswordTextInputLayout.error = ""
                pswCompleted = true
                benar()
            }
        }

        viewBind.NamaTextInputLayout.editText?.addTextChangedListener {
            var nama = viewBind.NamaTextInputLayout.editText?.text.toString().trim()
            if (nama.isEmpty()){
                viewBind.NamaTextInputLayout.error = "Tolong isi kolom nama"
                nameCompleted = false
                benar()
            }else
            {
                viewBind.NamaTextInputLayout.error = ""
                nameCompleted = true
                benar()
            }
        }

        viewBind.button.setOnClickListener {
            if(!x.isEmpty())
            {

                var lanjut = false
                for(i in 0..x.size-1)
                {
                    if(viewBind.NamaTextInputLayout.editText?.text.toString().trim().equals(x.get(i).nama))
                    {
                        index = i
                        lanjut = true
                        break
                    }
                }

                if(lanjut == true)
                {
                    if(viewBind.PasswordTextInputLayout.editText?.text.toString().trim().equals(x.get(index).password))
                    {
                        val myIntent = Intent(this, BottomnavbarActivity::class.java).apply {
                            putExtra("angka1", index)
                        }
                        startActivity(myIntent)
                    }else
                    {
                        viewBind.PasswordTextInputLayout.error = "Password salah"
                    }
                }else
                {
                    viewBind.NamaTextInputLayout.error = "Id salah"
                }
            }
            else
            {
                viewBind.button.error = "no users"
            }
        }
    }
    private fun benar()
    {
        if(pswCompleted && nameCompleted)
        {
            viewBind.button.isEnabled = true
        }else
        {
            viewBind.button.isEnabled = false
        }
    }
}