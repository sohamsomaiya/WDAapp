package com.example.wda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.GridView
import android.widget.Toast
import com.example.wda.adapter.DropdownWebNameAdapter
import com.example.wda.adapter.ViewQueryAdapter
import com.example.wda.api.User
import com.example.wda.model.Queries
import com.example.wda.model.UserWebsite
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
        var ID=""
        supportActionBar?.title="Query"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val prefrenceQuery = getSharedPreferences("wda", MODE_PRIVATE)
        val userid = prefrenceQuery.getString("userId", "")
        val contact = prefrenceQuery.getString("contact", "")
        Log.i("UId", userid.toString())
        CoroutineScope(Dispatchers.IO).launch {
            UserTemplates = User.getWebsiteDetails(userid.toString())

            ViewQueries=User.getQueries(contact.toString())

            if (UserTemplates.isEmpty()) {
  //              Toast.makeText(this@QueryActivity,"No Data Found", Toast.LENGTH_SHORT).show()
                withContext(Dispatchers.Main){
                    Toast.makeText(this@QueryActivity, "No Website Found", Toast.LENGTH_SHORT).show()
                    TemplateSpinnerLayout.visibility= View.GONE
                }
            } else {
                UserTemplates.also {
                    var UserTempadapter =
                            DropdownWebNameAdapter(this@QueryActivity, UserTemplates)
                    withContext(Dispatchers.Main){
                        TemplateSpinner.setAdapter(UserTempadapter)
                        TemplateSpinner.setOnItemClickListener { parent, view, position, id ->
                                 ID= UserTempadapter.getItem(position)!!.WebsiteId.toString()
                        }
                    }
                }
            }

            if (ViewQueries.isEmpty()) {
                withContext(Dispatchers.Main){
                    Toast.makeText(this@QueryActivity, "No Website Found", Toast.LENGTH_SHORT).show()
                }
            }
            else {
                withContext(Dispatchers.Main){
                    ViewQueries.also {
                            var ViewQueryAdapter =
                                ViewQueryAdapter(this@QueryActivity, ViewQueries)
                            ViewQueryGridView.setAdapter(ViewQueryAdapter)
                    }
                }
            }
        }


        QuerySubmitBtn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val UserQuery = User.raiseQuery(
                    UserQueryDescriptionTxt.text.toString(),
                    ID,
                    userid.toString()
                )
                withContext(Dispatchers.Main) {
                    if (UserQuery.getBoolean("success")) {
                        this@QueryActivity.recreate()
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

}