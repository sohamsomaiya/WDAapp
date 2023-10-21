package com.example.wda

import android.content.Intent
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Guideline
import com.example.wda.api.User
import com.google.android.material.datepicker.MaterialTextInputPicker
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CreateWebActivity : AppCompatActivity() {
    private lateinit var EnterDetailsSubmitbtn: Button
    private lateinit var guideline: Guideline
    private lateinit var constraintLayout: ConstraintLayout
    private lateinit var EnterDetailsTxt: TextView
    private lateinit var EnterDetailsSubTxt: TextView
    private lateinit var EnterDetailstxtLayout: TextInputLayout
    private lateinit var EnterDetailsTxtInput: TextInputEditText
    private lateinit var DOI: TextInputLayout
    private lateinit var COI: TextInputLayout
    private lateinit var TAN: TextInputLayout
    private lateinit var DOIInput: TextInputEditText
    private lateinit var TANInput: TextInputEditText
    private lateinit var COIInput: TextInputEditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_web)
        supportActionBar?.title=""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val getWebType=getSharedPreferences("wda", MODE_PRIVATE)
        val websiteType=getWebType.getString("WebsiteType","")
        Toast.makeText(this, websiteType, Toast.LENGTH_SHORT).show()

        EnterDetailsTxt=findViewById(R.id.EnterDetailsTxt)
        EnterDetailsSubTxt=findViewById(R.id.EnterDetailsSubTxt)
        EnterDetailstxtLayout=findViewById(R.id.EnterDetailstxtLayout)
        EnterDetailsTxtInput=findViewById(R.id.EnterDetailsTxtInput)
        DOI=findViewById(R.id.DOI)
        COI=findViewById(R.id.COI)
        TAN=findViewById(R.id.TAN)
        TANInput=findViewById(R.id.TANInput)
        DOIInput=findViewById(R.id.DOIInput)
        COIInput=findViewById(R.id.COIInput)
        EnterDetailsSubmitbtn=findViewById(R.id.EnterDetailsSubmitBtn)
        guideline=findViewById(R.id.EnterDetailsGuideline)
        constraintLayout=findViewById(R.id.LayoutEnterDetails)
        DOI.visibility=View.VISIBLE
        COI.visibility=View.VISIBLE
        TAN.visibility=View.VISIBLE


        if (websiteType=="Individual"){
            EnterDetailsTxt.text="Enter Your Website \nName!"
            EnterDetailsSubTxt.text="Enter Website Name To Get \nBetter Experience."
            DOI.visibility=View.GONE
            COI.visibility=View.GONE
            TAN.visibility=View.GONE

        }

        constraintLayout.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {

            override fun onGlobalLayout() {
                val rect = Rect()
                constraintLayout.getWindowVisibleDisplayFrame(rect)
                val screenHeight = constraintLayout.height
                val keypadHeight = screenHeight - rect.bottom

                if (keypadHeight > screenHeight * 0.15) {
                    guideline.setGuidelinePercent(0.6f)
                } else {
                    guideline.setGuidelinePercent(0.8F)
                }
            }
        })

        EnterDetailsSubmitbtn.setOnClickListener {
            Toast.makeText(this@CreateWebActivity, EnterDetailsTxtInput.text.toString(), Toast.LENGTH_SHORT).show()

            if( EnterDetailsTxtInput.text.toString()==""){
                Toast.makeText(this@CreateWebActivity, "Please Enter Website Name", Toast.LENGTH_SHORT).show()
            }else{
                CoroutineScope(Dispatchers.IO).launch {
                    val message= User.validateDomain(EnterDetailsTxtInput.text.toString())
                    withContext(Dispatchers.Main){
                        if (message.getBoolean("success")){
                            Toast.makeText(this@CreateWebActivity, message.getString("message"), Toast.LENGTH_SHORT).show()
                        }
                        else{
                            val CreateWebPrefrense = getSharedPreferences("wda", MODE_PRIVATE)
                            val editor = CreateWebPrefrense.edit()
                            editor.putString("UName", EnterDetailsTxtInput.text.toString())
                            editor.putString("UDOI",DOIInput.text.toString())
                            editor.putString("UCOI",COIInput.text.toString())
                            editor.putString("UTAN",TANInput.text.toString())
                            editor.apply()

                            val intent=Intent(this@CreateWebActivity,TemplateSelectionActivity::class.java)
                            startActivity(intent)
                            // finish()
                        }
                    }
                }
            }
        }
    }
}