package com.example.wda

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.ViewTreeObserver
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Guideline
import com.example.wda.api.User
import com.noobcode.otpview.OTPView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OtpActivity : AppCompatActivity() {
    private lateinit var Otp1 : OTPView
    private lateinit var OtpContinue : Button
    //private lateinit var Resend : Button
    private lateinit var OtpText: TextView
    private lateinit var SubTextOtp : TextView
    private lateinit var ResendOtpTxt : TextView
    private lateinit var guidelineOtp : Guideline
    private lateinit var ConstraintLayoutOtp : ConstraintLayout


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)


//        supportActionBar?.hide()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title=""
        Otp1=findViewById(R.id.OtpTxt)
        OtpContinue=findViewById(R.id.ContinueOtp)
//        Resend=findViewById(R.id.ResendOtpTxt)
        OtpText=findViewById(R.id.TxtOtp)
        SubTextOtp=findViewById(R.id.SubTxtOtp)
        ResendOtpTxt=findViewById(R.id.ResendOtpTxt)

        guidelineOtp = findViewById(R.id.guidelineOtp)
        ConstraintLayoutOtp = findViewById(R.id.ConstraintLayoutOtp)
        val Spref=getSharedPreferences("wda", MODE_PRIVATE)
        val Username=Spref.getString("name","")
        val Type= Spref.getString("type","")
        val ContactNo = Spref.getString("contact","")

        ResendOtpTxt.setOnClickListener {

            CoroutineScope(Dispatchers.IO).launch {
                val messege = User.sendOtp(Username!!, Type!!, ContactNo!!)
                withContext(Dispatchers.Main) {
                    if (!messege.getBoolean("success")) {
                        Toast.makeText(
                            this@OtpActivity,
                            messege.getString("message"),
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        val sp = getSharedPreferences("wda", MODE_PRIVATE)
                        val editor = sp.edit()
                        editor.putString("name", Username)
                        editor.putString("type", Type)
                        editor.putString("contact", ContactNo)
                        editor.apply()

                        val intent = Intent(this@OtpActivity, ContactActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            }
        }
        ConstraintLayoutOtp.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                val rect = Rect()
                ConstraintLayoutOtp.getWindowVisibleDisplayFrame(rect)
                val screenHeight = ConstraintLayoutOtp.height
                val keypadHeight = screenHeight - rect.bottom

                if (keypadHeight > screenHeight * 0.15) {
                    guidelineOtp.setGuidelinePercent(0.67f)
                } else {
                    guidelineOtp.setGuidelinePercent(1F)
                }
            }
        })



        val sh=getSharedPreferences("wda", MODE_PRIVATE)
        val name = sh.getString("name","")
        val contactNo=sh.getString("contact","")
        Toast.makeText(this, contactNo, Toast.LENGTH_SHORT).show()

        OtpContinue.setOnClickListener {
            val OTP =Otp1.text.toString()

            if (TextUtils.isEmpty(OTP)) {
                Toast.makeText(
                    this@OtpActivity,
                    "OTP Not Valid. Please try again..",
                    Toast.LENGTH_LONG
                ).show()
            }

                CoroutineScope(Dispatchers.IO).launch {
                    val messege = User.verifyOtp(name!!, OTP)
                    withContext(Dispatchers.Main) {
                        if (!messege.getBoolean("success")) {
                            Toast.makeText(
                                this@OtpActivity,
                                messege.getString("message"),
                                Toast.LENGTH_LONG
                            ).show()
                        } else {

                            val sp = getSharedPreferences("wda", MODE_PRIVATE)
                            val editor =sp.edit()
                            editor.putString("contact",contactNo)
                            editor.apply()

                            val intent = Intent(this@OtpActivity,SetPasswordActivity::class.java)
                            startActivity(intent)
                            finish()

                        }
                }

            }
        }
    }

}

