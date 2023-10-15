package com.example.wda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import com.example.wda.adapter.DropdownWebNameAdapter
import com.example.wda.api.User
import com.example.wda.model.UserWebsite
import com.example.wda.model.Website
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class QueryActivity : AppCompatActivity() {
    private lateinit var TemplateSpinner:AutoCompleteTextView
    private lateinit var TemplateSpinnerLayout:TextInputLayout
    private lateinit var UserQueryDescriptionTxt:TextInputEditText
    private lateinit var QuerySubmitBtn:Button
    private lateinit var UserTemplates : Array<UserWebsite>

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

                UserTemplates =User.getWebsiteDetails(userid.toString())

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

        QuerySubmitBtn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val UserQuery = User.raiseQuery(UserQueryDescriptionTxt.toString(), UserTemplates.toString(),userid.toString())
                if (UserQuery.getBoolean("success")){
                    val intent=Intent(this@QueryActivity,HomeActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this@QueryActivity, "Failed to raise Query", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}