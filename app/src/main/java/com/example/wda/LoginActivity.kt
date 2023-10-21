package com.example.wda

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.ViewTreeObserver
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Guideline
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
    private lateinit var CreateAccTxt : TextView
    private lateinit var guidelineLogin : Guideline
    private lateinit var ConstraintLayoutLogin : ConstraintLayout

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
        CreateAccTxt=findViewById(R.id.CreateAccTxt)

        guidelineLogin = findViewById(R.id.guidelineLogin)
        ConstraintLayoutLogin = findViewById(R.id.ConstraintLayoutLogin)

        ConstraintLayoutLogin.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                val rect = Rect()
                ConstraintLayoutLogin.getWindowVisibleDisplayFrame(rect)
                val screenHeight = ConstraintLayoutLogin.height
                val keypadHeight = screenHeight - rect.bottom

                if (keypadHeight > screenHeight * 0.15) {
                    guidelineLogin.setGuidelinePercent(0.7f)
                } else {
                    guidelineLogin.setGuidelinePercent(1F)
                }
            }
        })


        Password.addTextChangedListener {

            if (!Password.text.toString().contains(Regex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\S+\$).{8,}\$")))
            {
                Password.setError("password must contain 1 lower case letter [a-z] ,\n1 upper case letter [A-Z] ,\n1 numeric character [0-9]1 \nspecial character: ~`!@#\$%^&*()-_+={}[]|\\;:\"<>,./?",null)
//                Toast.makeText(this, "password must contain 1 lower case letter [a-z] ,\n1 upper case letter [A-Z] ,\n1 numeric character [0-9]1 \nspecial character: ~`!@#\$%^&*()-_+={}[]|\\;:\"<>,./?", Toast.LENGTH_SHORT).show()
            }

        }
        CreateAccTxt.setOnClickListener {
            val intent =Intent(this@LoginActivity,SignupActivity::class.java)
            startActivity(intent)
            finish()
        }
        Continue.setOnClickListener {

            if (TextUtils.isEmpty(MobileNumber.text.toString()) || TextUtils.isEmpty(Password.text.toString())) {
                Toast.makeText(
                    this@LoginActivity,
                    "Phone Number or Password cannot be empty",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (Password.text.toString().length < 8 ) {
                Toast.makeText(
                    this@LoginActivity,
                    "Password cannot be empty or must contain 8 characters",
                    Toast.LENGTH_SHORT
                ).show()
            }else{
                CoroutineScope(Dispatchers.IO).launch {
                    val messege = User.login(MobileNumber.text.toString(), Password.text.toString(),"user")

                    withContext(Dispatchers.Main) {
                        if (messege.getBoolean("success")) {
                            val sp = getSharedPreferences("wda", MODE_PRIVATE)
                            val editor=sp.edit()
                            editor.putString("userId",messege.getString("userID"))
                            editor.putString("contact",messege.getString("ContactNo"))
                            editor.putString("Username",messege.getString("Name"))
                            editor.apply()
                            val intent =Intent(this@LoginActivity,HomeActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this@LoginActivity,messege.getString("message"),Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }
}