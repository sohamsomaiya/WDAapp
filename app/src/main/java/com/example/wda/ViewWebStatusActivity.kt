package com.example.wda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.GridView
import android.widget.Toast
import com.example.wda.adapter.ViewQueryAdapter
import com.example.wda.adapter.ViewStatusAdapter
import com.example.wda.api.User
import com.example.wda.model.ListOfWebsite
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray

class ViewWebStatusActivity : AppCompatActivity() {
        private lateinit var ViewStatusGrid:GridView
        private lateinit var ViewStatus: Array<ListOfWebsite>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_web_status)
        ViewStatusGrid=findViewById(R.id.ViewStatusGrid)
        supportActionBar?.title="Website Status"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val prefrenceStatus = getSharedPreferences("wda", MODE_PRIVATE)
        val contact = prefrenceStatus.getString("contact", "")

        CoroutineScope(Dispatchers.IO).launch {
            ViewStatus = User.getWebsiteStatus(contact.toString())
            if (ViewStatus.equals(null)) {
                Toast.makeText(this@ViewWebStatusActivity, "No Data Found", Toast.LENGTH_SHORT).show()
                withContext(Dispatchers.Main){
                    Toast.makeText(this@ViewWebStatusActivity, "No Website Found", Toast.LENGTH_SHORT).show()
                }
            }
            else {
                withContext(Dispatchers.Main){
                    ViewStatus.also {
                        val ViewStatusAdapter =
                            ViewStatusAdapter(this@ViewWebStatusActivity, ViewStatus)
                        ViewStatusGrid.setAdapter(ViewStatusAdapter)
                    }
                }
            }
        }
    }
}