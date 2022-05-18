package com.a706012110039.signup

import com.a706012110039.signup.publicuser.Companion.x
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.a706012110039.signup.databinding.ActivitySignupBinding
import model.user

class signupActivity : AppCompatActivity() {
    private lateinit var viewBind: ActivitySignupBinding;
    private lateinit var user:user

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(viewBind.root
        )
        viewBind.button.isEnabled = false
        supportActionBar?.hide()
        listener()
    }
    var pswCompleted = false
    var nameCompleted = false
    var emailCompleted = false

    private fun listener() {
        viewBind.button.setOnClickListener {
            var nama = viewBind.NamaTextInputLayout.editText?.text.toString().trim()
            var email = viewBind.EmailTextInputLayout.editText?.text.toString().trim()
            var password = viewBind.PasswordTextInputLayout.editText?.text.toString().trim()

            user = user(nama, email, password)

            checker()
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

        viewBind.EmailTextInputLayout.editText?.addTextChangedListener {
            var email = viewBind.EmailTextInputLayout.editText?.text.toString().trim()
            if (email.isEmpty()){
                viewBind.EmailTextInputLayout.error = "Tolong isi kolom email"
                emailCompleted = false
                benar()
            }else
            {
                viewBind.EmailTextInputLayout.error = ""
                emailCompleted = true
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

        viewBind.signup.setOnClickListener {
            val myIntent = Intent(this, MainActivity::class.java)
            startActivity(myIntent)
            finish()
        }
    }



    private fun benar()
    {
        if(pswCompleted && nameCompleted && emailCompleted)
        {
            viewBind.button.isEnabled = true
        }else
        {
            viewBind.button.isEnabled = false
        }
    }


    private fun checker(){
        var isCompleted:Boolean = true

        //nama
        if(user.nama!!.isEmpty()){
            viewBind.NamaTextInputLayout.error = "Tolong isi kolom nama"
            isCompleted = false
        }else{
            viewBind.NamaTextInputLayout.error = ""
        }

        //Email
        if(user.email!!.isEmpty()){
            viewBind.EmailTextInputLayout.error = "Tolong isi kolom email"
            isCompleted = false
        }else{
            // Berguna untuk cek apakah input merupakan email
            if(!Patterns.EMAIL_ADDRESS.matcher(user.email).matches()){
                viewBind.EmailTextInputLayout.error = "Tolong masukan alamat email yang benar"
                isCompleted = false
            }else {
                viewBind.EmailTextInputLayout.error = ""
            }
        }

        // Password
        if (user.password!!.isEmpty()){
            viewBind.PasswordTextInputLayout.error = "Tolong isi kolom password"
            isCompleted = false
        }else{
            if (user.password!!.length < 8){
                viewBind.PasswordTextInputLayout.error = "Jumlah password min 8 karakter"
                isCompleted = false
            }else if(!user.password!!.matches(".*[a-z].*".toRegex())){
                viewBind.PasswordTextInputLayout.error = "Password tidak memiliki huruf kecil"
                isCompleted = false
            }else if(!user.password!!.matches(".*[A-Z].*".toRegex())){
                viewBind.PasswordTextInputLayout.error = "Password tidak memiliki huruf kapital"
                isCompleted = false
            }else{
                viewBind.PasswordTextInputLayout.error = ""
            }
        }

        if (isCompleted){
            val myIntent = Intent(this, MainActivity::class.java).apply {
                putExtra("status", "Sign up berhasil")
            }
            x.add(user)
            Toast.makeText(this, "sign up berhasil", Toast.LENGTH_LONG).show()
            startActivity(myIntent)
            finish()
        }
    }


}