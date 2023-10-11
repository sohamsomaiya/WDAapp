package com.example.wda

import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewTreeObserver
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Guideline

class CreateWebActivity : AppCompatActivity() {
    private lateinit var EnterDetailsSubmitbtn: Button
    private lateinit var guideline: Guideline
    private lateinit var constraintLayout: ConstraintLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_web)
        supportActionBar?.title=""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        EnterDetailsSubmitbtn=findViewById(R.id.EnterDetailsSubmitBtn)
        guideline=findViewById(R.id.EnterDetailsGuideline)
        constraintLayout=findViewById(R.id.LayoutEnterDetails)

        constraintLayout.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {

            override fun onGlobalLayout() {
                val rect = Rect()
                constraintLayout.getWindowVisibleDisplayFrame(rect)
                val screenHeight = constraintLayout.height
                val keypadHeight = screenHeight - rect.bottom

                if (keypadHeight > screenHeight * 0.15) {
                    guideline.setGuidelinePercent(0.75f)
                } else {
                    guideline.setGuidelinePercent(0.9F)
                }
            }
        })
    }
}