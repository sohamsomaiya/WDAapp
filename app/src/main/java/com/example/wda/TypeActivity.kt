package com.example.wda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.card.MaterialCardView

class TypeActivity : AppCompatActivity() {
    private lateinit var cardIndividual: MaterialCardView
    private lateinit var cardOrganization: MaterialCardView
    private lateinit var selectedCard:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_type)
        supportActionBar?.title=""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        cardIndividual=findViewById(R.id.IndividualCard)
        cardOrganization=findViewById(R.id.OrganizationCard)

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

    }
}