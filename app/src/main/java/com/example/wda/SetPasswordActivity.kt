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

class SetPasswordActivity : AppCompatActivity() {

    private lateinit var SetPasswod : EditText
    private lateinit var ConfirmPassword : EditText
    private lateinit var PasswordContinue : Button
    private lateinit var PasswordText: TextView
    private lateinit var SubTextPassword : TextView
    private lateinit var guidelineSetPassword : Guideline
    private lateinit var ConstLayoutSetPassword : ConstraintLayout

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_password)
        supportActionBar?.hide();
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        SetPasswod=findViewById(R.id.SetPasswordEditTxt)
        ConfirmPassword=findViewById(R.id.ConfirmPasswordEditTxt)
        PasswordContinue=findViewById(R.id.ContinueSetPassword)
        PasswordText=findViewById(R.id.TxtSetPassword)
        SubTextPassword=findViewById(R.id.SubTxtSetPassword)
        guidelineSetPassword = findViewById(R.id.guideline)
        ConstLayoutSetPassword = findViewById(R.id.ConstLayoutSetPassword)

        ConstLayoutSetPassword.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                val rect = Rect()
                ConstLayoutSetPassword.getWindowVisibleDisplayFrame(rect)
                val screenHeight = ConstLayoutSetPassword.height
                val keypadHeight = screenHeight - rect.bottom

                if (keypadHeight > screenHeight * 0.15) {
                    guidelineSetPassword.setGuidelinePercent(0.8f)
                } else {
                    guidelineSetPassword.setGuidelinePercent(1F)
                }
            }
        })
        val sh=getSharedPreferences("wda", MODE_PRIVATE)
        val ContactNo = sh.getString("contact","")
        val name= sh.getString("name","")
        SetPasswod.addTextChangedListener {
            if (!SetPasswod.toString().contains(Regex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\S+\$).{8,}\$")))
            {
                SetPasswod.setError("password must contain 1 lower case letter [a-z] ,\n1 upper case letter [A-Z] ,\n1 numeric character [0-9]1 \nspecial character: ~`!@#\$%^&*()-_+={}[]|\\;:\"<>,./?",null)
//                Toast.makeText(this, "password must contain 1 lower case letter [a-z] ,\n1 upper case letter [A-Z] ,\n1 numeric character [0-9]1 \nspecial character: ~`!@#\$%^&*()-_+={}[]|\\;:\"<>,./?", Toast.LENGTH_SHORT).show()
            }
            else if(SetPasswod.text.toString().length < 8){
                SetPasswod.setError("Password must contain 8 ")
            }
        }

        PasswordContinue.setOnClickListener {
            var password = SetPasswod.text.toString()
            var confirmpassword = ConfirmPassword.text.toString()

            if (TextUtils.isEmpty(password)) {
                Toast.makeText(
                    this@SetPasswordActivity,
                    "Password cannot be empty",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (TextUtils.isEmpty(confirmpassword)) {
                Toast.makeText(
                    this@SetPasswordActivity,
                    "Confirm Password cannot be empty",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else if(password.length < 8){
                SetPasswod.setError("Password must contain 8 ")
            }

                CoroutineScope(Dispatchers.IO).launch {
                    val messege = User.registerUser(name!!,password,confirmpassword)
                    withContext(Dispatchers.Main) {
                        if (!messege.getBoolean("success")) {
                            Toast.makeText(
                                this@SetPasswordActivity,
                                messege.getString("message"),
                                Toast.LENGTH_LONG
                            ).show()
                        } else {
                            val sp = getSharedPreferences("wda", MODE_PRIVATE)
                            val editor = sp.edit()
                            editor.putString("contact", ContactNo)
                            editor.putString("password", password)
                            editor.apply()

                            val intent = Intent(this@SetPasswordActivity, LoginActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }

            }
        }
    }
}