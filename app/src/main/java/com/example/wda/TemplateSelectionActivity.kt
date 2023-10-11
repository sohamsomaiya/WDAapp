package com.example.wda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.card.MaterialCardView

class TemplateSelectionActivity : AppCompatActivity() {
    private lateinit var Template1:MaterialCardView
    private lateinit var Template2:MaterialCardView
    private lateinit var Template1_2:MaterialCardView
    private lateinit var Template2_2:MaterialCardView
    private lateinit var Template1_3:MaterialCardView
    private lateinit var Template2_3:MaterialCardView
    private lateinit var Template1_4:MaterialCardView
    private lateinit var Template2_4:MaterialCardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_template_selection)
        supportActionBar?.title="Select Templates"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        Template1=findViewById(R.id.Template1)
        Template2=findViewById(R.id.Template2)
        Template1_2=findViewById(R.id.Template1_2)
        Template2_2=findViewById(R.id.Template2_2)
        Template1_3=findViewById(R.id.Template1_3)
        Template2_3=findViewById(R.id.Template2_3)
        Template1_4=findViewById(R.id.Template1_4)
        Template2_4=findViewById(R.id.Template2_4)

        Template1.setOnClickListener {
            Template1.isChecked=true
            Template2.isChecked=false
            Template1_2.isChecked=false
            Template2_2.isChecked=false
            Template1_3.isChecked=false
            Template2_3.isChecked=false
            Template1_4.isChecked=false
            Template2_4.isChecked=false
            Template1.strokeWidth=2
            Template2.strokeWidth=0
            Template1_2.strokeWidth=0
            Template2_2.strokeWidth=0
            Template1_3.strokeWidth=0
            Template2_3.strokeWidth=0
            Template1_4.strokeWidth=0
            Template2_4.strokeWidth=0
        }
        Template2.setOnClickListener {
            Template1.isChecked=false
            Template2.isChecked=true
            Template1_2.isChecked=false
            Template2_2.isChecked=false
            Template1_3.isChecked=false
            Template2_3.isChecked=false
            Template1_4.isChecked=false
            Template2_4.isChecked=false
            Template1.strokeWidth=0
            Template2.strokeWidth=2
            Template1_2.strokeWidth=0
            Template2_2.strokeWidth=0
            Template1_3.strokeWidth=0
            Template2_3.strokeWidth=0
            Template1_4.strokeWidth=0
            Template2_4.strokeWidth=0

        }
        Template1_2.setOnClickListener {
            Template1.isChecked=false
            Template2.isChecked=false
            Template1_2.isChecked=true
            Template2_2.isChecked=false
            Template1_3.isChecked=false
            Template2_3.isChecked=false
            Template1_4.isChecked=false
            Template2_4.isChecked=false
            Template1.strokeWidth=0
            Template2.strokeWidth=0
            Template1_2.strokeWidth=2
            Template2_2.strokeWidth=0
            Template1_3.strokeWidth=0
            Template2_3.strokeWidth=0
            Template1_4.strokeWidth=0
            Template2_4.strokeWidth=0
        }
        Template2_2.setOnClickListener {
            Template1.isChecked=false
            Template2.isChecked=false
            Template1_2.isChecked=false
            Template2_2.isChecked=true
            Template1_3.isChecked=false
            Template2_3.isChecked=false
            Template1_4.isChecked=false
            Template2_4.isChecked=false
            Template1.strokeWidth=0
            Template2.strokeWidth=0
            Template1_2.strokeWidth=0
            Template2_2.strokeWidth=2
            Template1_3.strokeWidth=0
            Template2_3.strokeWidth=0
            Template1_4.strokeWidth=0
            Template2_4.strokeWidth=0
        }
        Template1_3.setOnClickListener {
            Template1.isChecked=false
            Template2.isChecked=false
            Template1_2.isChecked=false
            Template2_2.isChecked=false
            Template1_3.isChecked=true
            Template2_3.isChecked=false
            Template1_4.isChecked=false
            Template2_4.isChecked=false
            Template1.strokeWidth=0
            Template2.strokeWidth=0
            Template1_2.strokeWidth=0
            Template2_2.strokeWidth=0
            Template1_3.strokeWidth=2
            Template2_3.strokeWidth=0
            Template1_4.strokeWidth=0
            Template2_4.strokeWidth=0
        }
        Template2_3.setOnClickListener {
            Template1.isChecked=false
            Template2.isChecked=false
            Template1_2.isChecked=false
            Template2_2.isChecked=false
            Template1_3.isChecked=false
            Template2_3.isChecked=true
            Template1_4.isChecked=false
            Template2_4.isChecked=false
            Template1.strokeWidth=0
            Template2.strokeWidth=0
            Template1_2.strokeWidth=0
            Template2_2.strokeWidth=0
            Template1_3.strokeWidth=0
            Template2_3.strokeWidth=2
            Template1_4.strokeWidth=0
            Template2_4.strokeWidth=0
        }
        Template1_4.setOnClickListener {
            Template1.isChecked=false
            Template2.isChecked=false
            Template1_2.isChecked=false
            Template2_2.isChecked=false
            Template1_3.isChecked=false
            Template2_3.isChecked=false
            Template1_4.isChecked=true
            Template2_4.isChecked=false
            Template1.strokeWidth=0
            Template2.strokeWidth=0
            Template1_2.strokeWidth=0
            Template2_2.strokeWidth=0
            Template1_3.strokeWidth=0
            Template2_3.strokeWidth=0
            Template1_4.strokeWidth=2
            Template2_4.strokeWidth=0
        }
        Template2_4.setOnClickListener {
            Template1.isChecked=false
            Template2.isChecked=false
            Template1_2.isChecked=false
            Template2_2.isChecked=false
            Template1_3.isChecked=false
            Template2_3.isChecked=false
            Template1_4.isChecked=false
            Template2_4.isChecked=true
            Template1.strokeWidth=0
            Template2.strokeWidth=0
            Template1_2.strokeWidth=0
            Template2_2.strokeWidth=0
            Template1_3.strokeWidth=0
            Template2_3.strokeWidth=0
            Template1_4.strokeWidth=0
            Template2_4.strokeWidth=2
        }
    }
}