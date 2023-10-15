package com.example.wda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import com.example.wda.adapter.DropdownWebNameAdapter
import com.example.wda.api.User
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class QueryActivity : AppCompatActivity() {
    private lateinit var TemplateSpinner:AutoCompleteTextView
    private lateinit var TemplateSpinnerLayout:TextInputLayout
    private lateinit var UserQueryDescriptionTxt:TextInputLayout
    private lateinit var QuerySubmitBtn:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_query)
        TemplateSpinner=findViewById(R.id.TemplateAutoCompleteTextView)
        TemplateSpinnerLayout=findViewById(R.id.TemplateSelectLayout)
        UserQueryDescriptionTxt=findViewById(R.id.UserQueryDescriptionTxt)
        QuerySubmitBtn=findViewById(R.id.QuerySubmitBtn)

        val prefrenceQuery = getSharedPreferences("wda", MODE_PRIVATE)
        val userid = prefrenceQuery.getString("userId","")
        Log.i("UId",userid.toString())
        CoroutineScope(Dispatchers.IO).launch {

                val UserTemplates =User.getWebsiteDetails(userid.toString())
            Log.i("User Template",UserTemplates.toString())
            if(UserTemplates.isEmpty()){
                Toast.makeText(this@QueryActivity, "No Website Found", Toast.LENGTH_SHORT).show()
            }else{
                UserTemplates.also {
                    withContext(Dispatchers.Main){
                        var UserTempadapter = DropdownWebNameAdapter(this@QueryActivity,UserTemplates)
                        TemplateSpinner.setAdapter(UserTempadapter)

                    }
                }
            }
        }


    }
}