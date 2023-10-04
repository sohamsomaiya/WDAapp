package com.example.wda

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.view.ViewTreeObserver
import android.view.WindowManager
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

class ContactActivity : AppCompatActivity() {
    private lateinit var Contact : EditText
    private lateinit var ContactContinue : Button
    private lateinit var MobileText: TextView
    private lateinit var SubTextMobile : TextView
    private lateinit var guideline: Guideline
    private var type = "user"
    private lateinit var constraintLayout: ConstraintLayout

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

//        supportActionBar?.hide();
        supportActionBar?.title=""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        constraintLayout = findViewById(R.id.constraintLayout)
        ContactContinue=findViewById(R.id.ContinueContact)
        guideline = findViewById(R.id.guideline)

        constraintLayout.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {

            override fun onGlobalLayout() {
                val rect = Rect()
                constraintLayout.getWindowVisibleDisplayFrame(rect)
                val screenHeight = constraintLayout.height
                val keypadHeight = screenHeight - rect.bottom

                if (keypadHeight > screenHeight * 0.15) {
                    guideline.setGuidelinePercent(0.8f)
                } else {
                    guideline.setGuidelinePercent(1F)
                }
            }
        })

        // Prevent the keyboard from pushing up the layout
//        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        Contact=findViewById(R.id.ContactEditTxt)
        MobileText=findViewById(R.id.TxtContact)
        SubTextMobile=findViewById(R.id.SubTxtContact)
        Contact.addTextChangedListener {
            Contact.toString().contains(Regex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\S+\$).{8,}\$"))
        }
        val sh=getSharedPreferences("wda", MODE_PRIVATE)
        val name=sh.getString("name","")
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show()

        ContactContinue.setOnClickListener {
            var contactNo = Contact.text.toString()
            if (TextUtils.isEmpty(contactNo)){
                Toast.makeText(this@ContactActivity, "Contact Number cannot be Empty", Toast.LENGTH_SHORT).show()
            }else{
            CoroutineScope(Dispatchers.IO).launch {
                val messege = User.sendOtp(name!!, type, contactNo)
                withContext(Dispatchers.Main) {
                    if (!messege.getBoolean("success")) {
                        Toast.makeText(
                            this@ContactActivity,
                            messege.getString("message"),
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        val sp = getSharedPreferences("wda", MODE_PRIVATE)
                        val editor = sp.edit()
                        editor.putString("name", name)
                        editor.putString("type", type)
                        editor.putString("contact", contactNo)
                        editor.apply()

                        val intent = Intent(this@ContactActivity, OtpActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            }
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        menuInflater.inflate(R.menu.action_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.english -> {
//                Toast.makeText(applicationContext, "english", Toast.LENGTH_SHORT).show()
//                return true
//            }
//            R.id.hindi -> {
//                Toast.makeText(applicationContext, "hindi", Toast.LENGTH_SHORT).show()
//                return true
//            }
//        }
        return super.onOptionsItemSelected(item)
    }
}