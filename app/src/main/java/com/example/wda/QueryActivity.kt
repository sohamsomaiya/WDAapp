package com.example.wda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.GridView
import android.widget.Toast
import com.example.wda.adapter.DropdownWebNameAdapter
import com.example.wda.adapter.ViewQueryAdapter
import com.example.wda.api.User
import com.example.wda.model.Queries
import com.example.wda.model.UserWebsite
import com.example.wda.model.Website
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class QueryActivity : AppCompatActivity() {
    private lateinit var TemplateSpinner: AutoCompleteTextView
    private lateinit var TemplateSpinnerLayout: TextInputLayout
    private lateinit var UserQueryDescriptionTxt: TextInputEditText
    private lateinit var QuerySubmitBtn: Button
    private lateinit var ViewQueryGridView: GridView
    private lateinit var UserTemplates: Array<UserWebsite>
    private lateinit var ViewQueries: Array<Queries>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_query)
        TemplateSpinner = findViewById(R.id.TemplateAutoCompleteTextView)
        TemplateSpinnerLayout = findViewById(R.id.TemplateSelectLayout)
        UserQueryDescriptionTxt = findViewById(R.id.UserQueryDescriptionTxt)
        QuerySubmitBtn = findViewById(R.id.QuerySubmitBtn)
        ViewQueryGridView = findViewById(R.id.ViewQueryGridView)
        val ID=""
        val prefrenceQuery = getSharedPreferences("wda", MODE_PRIVATE)
        val userid = prefrenceQuery.getString("userId", "")
        val contact = prefrenceQuery.getString("contact", "")
        Log.i("UId", userid.toString())
        CoroutineScope(Dispatchers.IO).launch {

            UserTemplates = User.getWebsiteDetails(userid.toString())
            ViewQueries=User.getQueries(contact.toString())

            //Log.i("User Template",UserTemplates.toString())
            if (UserTemplates.isEmpty()) {
                Toast.makeText(this@QueryActivity, "No Website Found", Toast.LENGTH_SHORT).show()
            } else {
                UserTemplates.also {
                    withContext(Dispatchers.Main) {
                        var UserTempadapter =
                            DropdownWebNameAdapter(this@QueryActivity, UserTemplates)
                        TemplateSpinner.setAdapter(UserTempadapter)
                        TemplateSpinner.setOnItemClickListener { parent, view, position, id ->
                            var ID=UserTempadapter.getItem(position)!!.WebsiteId
//                            Log.i("TEmplate Id",ID.toString())


                        }

                    }
                }
            }
//            if (ViewQueries.isEmpty()) {
//                Toast.makeText(this@QueryActivity, "No Website Found", Toast.LENGTH_SHORT).show()
//            } else {
//                ViewQueries.also {
//                    withContext(Dispatchers.Main) {
//                        var ViewQueryAdapter =
//                            ViewQueryAdapter(this@QueryActivity, ViewQueries)
//                        TemplateSpinner.setAdapter(ViewQueryAdapter)
//                    }
//                }
//            }
//            Log.i("Number of templates", UserTemplates[1].toString())
        }


        QuerySubmitBtn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val UserQuery = User.raiseQuery(
                    UserQueryDescriptionTxt.toString(),
                    ID,
                    userid.toString()
                )
                if (UserQuery.getBoolean("success")) {
                    val intent = Intent(this@QueryActivity, HomeActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        this@QueryActivity,
                        UserQuery.getString("message"),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

}