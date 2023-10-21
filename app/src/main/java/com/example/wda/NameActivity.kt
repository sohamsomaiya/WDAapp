package com.example.wda

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.res.Resources
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.view.ViewTreeObserver
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Guideline
import kotlinx.coroutines.*

class NameActivity : AppCompatActivity() {
        private lateinit var Name : EditText
        private lateinit var NameContinue : Button
        private lateinit var NameText:TextView
        private lateinit var SubTextYourName :TextView
        private lateinit var ConstraintLayoutName :ConstraintLayout
        private lateinit var guidelineName :Guideline


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the app's locale based on the saved language preference
        setContentView(R.layout.activity_name)

//        CoroutineScope(Dispatchers.IO).launch {
            val selectedLanguage = LocaleHelper.getSelectedLanguage(this@NameActivity)
                LocaleHelper.setLocale(this@NameActivity, selectedLanguage)
//            withContext(Dispatchers.Main){
//            }
//        }


//        this.recreate()
//        supportActionBar?.hide();
        supportActionBar?.title=""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        Name=findViewById(R.id.YourNameEditTxt)
        NameContinue=findViewById(R.id.ContinueName)
        NameText=findViewById(R.id.TxtYourName)
        SubTextYourName=findViewById(R.id.SubTxtYourName)

        guidelineName = findViewById(R.id.guidelineName)
        ConstraintLayoutName = findViewById(R.id.ConstraintLayoutName)

        ConstraintLayoutName.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                val rect = Rect()
                ConstraintLayoutName.getWindowVisibleDisplayFrame(rect)
                val screenHeight = ConstraintLayoutName.height
                val keypadHeight = screenHeight - rect.bottom

                if (keypadHeight > screenHeight * 0.15) {
                    guidelineName.setGuidelinePercent(0.7f)
                } else {
                    guidelineName.setGuidelinePercent(1F)
                }
            }
        })
        NameContinue.setOnClickListener{
            var name = Name.text.toString()
            if(TextUtils.isEmpty(Name.text)){
                Toast.makeText(this@NameActivity, getString(R.string.name_not_entered), Toast.LENGTH_SHORT).show()
            }
            else{
                val sp = getSharedPreferences("wda", MODE_PRIVATE)
                val editor =sp.edit()
                editor.putString("name",name)
                editor.apply()

                val intent=Intent(this@NameActivity,ContactActivity::class.java)
                startActivity(intent)
//                finish()
            }
        }



    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.action_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.english -> {
//                Resources.getSystem().flushLayoutCache()
                // Change the language to English
                // Save the selected language to SharedPreferences
                LocaleHelper.persistSelectedLanguage(this, "en")

                LocaleHelper.updateResourcesLegacy(this, "en")

                LocaleHelper.setLocale(this, "en")

//                Toast.makeText(applicationContext, "English", Toast.LENGTH_SHORT).show()
                // Recreate the activity to apply the new locale
//                finish()

                // Relaunch the app by starting the main activity
                val intent = Intent(this, SignupActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
//                recreate()
                return true
            }
            R.id.gujarati -> {
//                Resources.getSystem().flushLayoutCache()
                LocaleHelper.updateResourcesLegacy(this, "gu")
                // Save the selected language to SharedPreferences
                LocaleHelper.persistSelectedLanguage(this, "gu")
                // Change the language to Hindi
                LocaleHelper.setLocale(this, "gu")
                // Recreate the activity to apply the new locale
//                finish()

                // Relaunch the app by starting the main activity
                val intent = Intent(this, SignupActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
//                recreate()
//                Toast.makeText(applicationContext, "Gujarati", Toast.LENGTH_SHORT).show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    //        return true
    }
}