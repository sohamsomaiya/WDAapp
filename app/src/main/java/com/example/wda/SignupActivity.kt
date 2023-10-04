package com.example.wda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class SignupActivity : AppCompatActivity() {
    private lateinit var SignUpBtn:Button
    private lateinit var LoginTxtBtn:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
//        val selectedLanguage = LocaleHelper.getSelectedLanguage(this)
//        LocaleHelper.setLocale(this, selectedLanguage)
//        supportActionBar?.hide()
        supportActionBar?.title=""

        SignUpBtn=findViewById(R.id.SignUpBtn)
        LoginTxtBtn=findViewById(R.id.LoginBtn)

        SignUpBtn.setOnClickListener {
            val signup =
                Intent(this, NameActivity::class.java)
            startActivity(signup)
        }
        LoginTxtBtn.setOnClickListener {
            val login=Intent(this,LoginActivity::class.java)
            startActivity(login)
        }

    }

}