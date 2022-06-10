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
    var alamatCompleted = false
    var creditCompleted = false

    private fun listener() {
        viewBind.button.setOnClickListener {
            var nama = viewBind.NamaTextInputLayout.editText?.text.toString().trim()
            var email = viewBind.EmailTextInputLayout.editText?.text.toString().trim()
            var password = viewBind.PasswordTextInputLayout.editText?.text.toString().trim()
            var alamat = viewBind.AlamatTextInputLayout.editText?.text.toString().trim()
            var credit_card = viewBind.CreditCardTextInputLayout.editText?.text.toString().trim()

            user = user(nama, email, password, alamat, credit_card)

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
        viewBind.AlamatTextInputLayout.editText?.addTextChangedListener {
            var alamat = viewBind.AlamatTextInputLayout.editText?.text.toString().trim()
            if (alamat.isEmpty()){
                viewBind.AlamatTextInputLayout.error = "Tolong isi kolom alamat"
                alamatCompleted = false
                benar()
            }else
            {
                viewBind.AlamatTextInputLayout.error = ""
                alamatCompleted = true
                benar()
            }
        }
        viewBind.CreditCardTextInputLayout.editText?.addTextChangedListener {
            var cc = viewBind.CreditCardTextInputLayout.editText?.text.toString().trim()
            if (cc.isEmpty()){
                viewBind.CreditCardTextInputLayout.error = "Tolong isi kolom password"
                creditCompleted = false
                benar()
            }else
            {
                viewBind.CreditCardTextInputLayout.error = ""
                creditCompleted = true
                benar()
            }
        }
        viewBind.textView2.setOnClickListener {
            finish()
        }
    }



    private fun benar()
    {
        if(pswCompleted && nameCompleted && emailCompleted && alamatCompleted && creditCompleted)
        {
            viewBind.button.isEnabled = true
        }else
        {
            viewBind.button.isEnabled = false
        }
    }


    private fun checker(){
        var isCompleted:Boolean = true
        var namaerror: Int = 0
        //nama tidak boleh sama
        if(!x.isEmpty()){
            for(i in 0..x.size-1){
                if(user.nama == x.get(i).nama){
                    namaerror = 1
                    break
                }
            }
        }
        if(user.nama!!.isEmpty()){
            viewBind.NamaTextInputLayout.error = "Tolong isi kolom nama"
            isCompleted = false
        }
        else if(namaerror == 1){
            viewBind.NamaTextInputLayout.error = "username taken"
            isCompleted = false
        }
        else
        {
            viewBind.NamaTextInputLayout.error = ""
        }

        //credit card
        if(user.nama!!.isEmpty()){
            viewBind.CreditCardTextInputLayout.error = "Tolong isi kolom Credit card"
            isCompleted = false
        }else{
            viewBind.CreditCardTextInputLayout.error = ""
        }

        //alamat
        if(user.alamat!!.isEmpty()){
            viewBind.AlamatTextInputLayout.error = "Tolong isi kolom alamat"
            isCompleted = false
        }else{
            viewBind.AlamatTextInputLayout.error = ""
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
            x.add(user)
            Toast.makeText(this, "sign up berhasil", Toast.LENGTH_LONG).show()
            finish()
        }
    }


}