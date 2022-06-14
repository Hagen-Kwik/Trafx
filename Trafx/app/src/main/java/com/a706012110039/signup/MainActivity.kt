package com.a706012110039.signup

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.a706012110039.signup.publicuser.Companion.x
import androidx.core.widget.addTextChangedListener
import model.user
import com.a706012110039.signup.databinding.ActivityMainBinding
import database.globalvar
import kotlinx.android.synthetic.main.activity_profile.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewBind: ActivityMainBinding;
    private lateinit var user:user
    lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBind.root)
        supportActionBar?.hide()
        viewBind.button.isEnabled = false
        listener()
        x.add(user("a","a","a","a","1","2000000"))

        sharedPreferences = getSharedPreferences("a",Context.MODE_PRIVATE)
        if(sharedPreferences.contains("username")) {
            var uname = sharedPreferences.getString("username", ".")
            for(i in 0..x.size-1)
            {
                if(uname.equals(x.get(i).nama))
                {
                    index = i
                    break
                }
            }
            globalvar.curuser = index
            val myIntent = Intent(this, BottomnavbarActivity::class.java).apply {
                putExtra("angka1", index)
            }
            startActivity(myIntent)
            finish()

        }

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
                        globalvar.curuser = index
                        var n = viewBind.NamaTextInputLayout.editText?.text.toString().trim()
                        val editor:SharedPreferences.Editor =  sharedPreferences.edit()
                        editor.putString("username", n)
                        editor.apply()
                        editor.commit()

                        val myIntent = Intent(this, BottomnavbarActivity::class.java).apply {
                            putExtra("angka1", index)
                        }
                        startActivity(myIntent)
                        finish()
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