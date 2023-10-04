package com.example.wda

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.example.wda.api.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {

    private lateinit var MobileNumber : EditText
    private lateinit var Password : EditText
    private lateinit var Continue : Button
    private lateinit var LoginText: TextView
    private lateinit var SubTextLogin : TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide();
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        MobileNumber=findViewById(R.id.MobileNumberEditTxt)
        Password=findViewById(R.id.EnterPasswordEditTxt)
        Continue=findViewById(R.id.ContinueLogin)
        LoginText=findViewById(R.id.TxtLogin)
        SubTextLogin=findViewById(R.id.SubTxtLogin)

        val sh=getSharedPreferences("wda", MODE_PRIVATE)
        val ContactNo = sh.getString("contact","")
        val password = sh.getString("password","")
        Password.addTextChangedListener {

            if (!Password.text.toString().contains(Regex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\S+\$).{8,}\$")))
            {
                Password.setError("password must contain 1 lower case letter [a-z] ,\n1 upper case letter [A-Z] ,\n1 numeric character [0-9]1 \nspecial character: ~`!@#\$%^&*()-_+={}[]|\\;:\"<>,./?",null)
//                Toast.makeText(this, "password must contain 1 lower case letter [a-z] ,\n1 upper case letter [A-Z] ,\n1 numeric character [0-9]1 \nspecial character: ~`!@#\$%^&*()-_+={}[]|\\;:\"<>,./?", Toast.LENGTH_SHORT).show()
            }

        }
        Continue.setOnClickListener {
            val phoneNo = ContactNo.toString()
            val password = password.toString()

            if (TextUtils.isEmpty(phoneNo)) {
                Toast.makeText(
                    this@LoginActivity,
                    "Phone Number cannot be empty",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (TextUtils.isEmpty(password) && password.length < 8 ) {
                Toast.makeText(
                    this@LoginActivity,
                    "Password cannot be empty or must contain 8 characters",
                    Toast.LENGTH_SHORT
                ).show()
            }


            CoroutineScope(Dispatchers.IO).launch {
                val messege = User.login(ContactNo!!, password!!,"user")
                withContext(Dispatchers.Main) {
                    if (messege.getBoolean("success")) {

                        Toast.makeText(
                            this@LoginActivity,
                            "LoggedIN",
                            Toast.LENGTH_LONG
                        ).show()
                        val homeintent = Intent(this@LoginActivity,HomeActivity::class.java)
                        startActivity(homeintent)
                    } else {
                        Toast.makeText(this@LoginActivity,messege.getString("message"),Toast.LENGTH_LONG).show()
                    }

                }
            }


        }
    }
}