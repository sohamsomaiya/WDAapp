package com.example.wda

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View.OnTouchListener
import android.webkit.*
import android.widget.Button
import android.widget.GridLayout
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.wda.R.id.SelectTemplateGridView
import com.example.wda.adapter.SelectTemplateAdapter
import com.example.wda.api.User
import com.example.wda.model.Templates
import com.google.android.material.card.MaterialCardView
import kotlinx.coroutines.*
import java.io.ByteArrayOutputStream
import java.util.zip.GZIPOutputStream

class TemplateSelectionActivity : AppCompatActivity() {
//    private lateinit var Template1: MaterialCardView
//    private lateinit var Template2: MaterialCardView
//    private lateinit var Template1_2: MaterialCardView
//    private lateinit var Template2_2: MaterialCardView
//    private lateinit var Template1_3: MaterialCardView
//    private lateinit var Template2_3: MaterialCardView
//    private lateinit var Template1_4: MaterialCardView
//    private lateinit var Template2_4: MaterialCardView
//    private lateinit var SelectTemplateGridLayout: GridLayout
    private lateinit var TemplateSelecctSubmitBtn: Button
    private lateinit var SelectTemplateGridView: GridView
    private lateinit var templateGif:Unit


    @SuppressLint("JavascriptInterface", "ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_template_selection)
        supportActionBar?.title = "Select Templates"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        TemplateSelecctSubmitBtn=findViewById(R.id.TemplateSelecctSubmitBtn)
        SelectTemplateGridView=findViewById(R.id.SelectTemplateGridView)

        CoroutineScope(Dispatchers.IO).launch {
            var message = User.getAllTemplate()


            for (imageName in message){
                templateGif=User.downloadWebsiteGif(this@TemplateSelectionActivity,imageName.TemplateImage)
            }

            withContext(Dispatchers.Main) {
                if (message.isEmpty()) {
                    Toast.makeText(
                        this@TemplateSelectionActivity,
                        "No template found",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    SelectTemplateGridView.adapter=SelectTemplateAdapter(this@TemplateSelectionActivity,message,TemplateSelecctSubmitBtn)

                }
            }
        }
    }
}

