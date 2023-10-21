package com.example.wda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.card.MaterialCardView

class TypeActivity : AppCompatActivity() {
    private lateinit var cardIndividual: MaterialCardView
    private lateinit var cardOrganization: MaterialCardView
    private var selectedCard:String = ""
    private lateinit var TypeSubmitBtn:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_type)
        supportActionBar?.title=""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        cardIndividual=findViewById(R.id.IndividualCard)
        cardOrganization=findViewById(R.id.OrganizationCard)
        TypeSubmitBtn=findViewById(R.id.continuetype)

        cardIndividual.setOnClickListener {
            cardIndividual.isChecked=true
            cardOrganization.isChecked=false
            cardOrganization.strokeWidth=0
            cardIndividual.strokeWidth=3
            selectedCard="Individual"
        }
        cardOrganization.setOnClickListener {
            cardIndividual.isChecked=false
            cardOrganization.isChecked=true
            cardOrganization.strokeWidth=3
            cardIndividual.strokeWidth=0
            selectedCard="Organization"
        }
        TypeSubmitBtn.setOnClickListener {

           // Toast.makeText(this, selectedCard, Toast.LENGTH_SHORT).show()
            if(selectedCard==""){
                Toast.makeText(this, "Please choose the type of Website", Toast.LENGTH_SHORT).show()
            }
            else{
                val webType = getSharedPreferences("wda", MODE_PRIVATE)
                val editor=webType.edit()
                editor.putString("WebsiteType",selectedCard)
                editor.apply()

                var intent = Intent(this@TypeActivity,CreateWebActivity::class.java)
                startActivity(intent)
            }
        }
    }
}