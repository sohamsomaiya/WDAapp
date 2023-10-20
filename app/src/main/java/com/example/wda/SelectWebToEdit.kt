package com.example.wda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.GridView
import android.widget.ImageView
import android.widget.Toast
import com.example.wda.adapter.SelectTemplateAdapter
import com.example.wda.adapter.SelectUsersTempalteToEditAdapter
import com.example.wda.api.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.stream.IntStream.range

class SelectWebToEdit : AppCompatActivity() {
    private lateinit var SelectToEditGrid : GridView
    private lateinit var SelectToEditSubmit : Button
    private lateinit var downloadLogo : Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_web_to_edit)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title="Select Website To Edit"
        SelectToEditGrid = findViewById(R.id.SelectToEditGrid)

        SelectToEditSubmit = findViewById(R.id.SelectToEditSubmit)
            val SelectEditPrefrence = getSharedPreferences("wda", MODE_PRIVATE)
            val UId = SelectEditPrefrence.getString("userId","")
//            val tpath = SelectEditPrefrence.getString("TemplatePath","")
            val editor = SelectEditPrefrence.edit()
            editor.remove("TemplatePath").apply()

        CoroutineScope(Dispatchers.IO).launch {
            val message = User.getWebsiteDetails(UId.toString())
            var i = 0
            for(i in range(0,message.size)){
                downloadLogo = User.downloadLogo(this@SelectWebToEdit,message[i].WebsiteName.toString())
            }

//            Log.i("logo",message[4].WebsiteName.toString())

            withContext(Dispatchers.Main) {
                if (message.isEmpty()) {
                    Toast.makeText(
                        this@SelectWebToEdit,
                        "No template found",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    SelectToEditGrid.adapter= SelectUsersTempalteToEditAdapter(this@SelectWebToEdit,message,SelectToEditSubmit,downloadLogo)

                }
            }
        }
    }
}