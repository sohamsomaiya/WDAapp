package com.example.wda

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.icu.util.LocaleData
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.core.widget.doOnTextChanged
import com.google.android.material.datepicker.MaterialTextInputPicker
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.time.LocalDate
import java.util.*

class ProfileActivity : AppCompatActivity() {
    private lateinit var ProfileLayout:LinearLayout
    private lateinit var EditProfileLayout:LinearLayout
    private lateinit var EditProfileBtn:ImageButton
    private lateinit var EditProfileSaveBtn:Button
    private lateinit var EditProfileCancleBtn:Button
    private lateinit var UserNameText: TextView
    private lateinit var GenderText: TextView
    private lateinit var DOBText: TextView
    private lateinit var PhoneText: TextView
    private lateinit var AddressText: TextView
    private lateinit var profileNameEditTxt: TextView
    private lateinit var ProfileEditGenderRadioGroup: RadioGroup
    private lateinit var profileDOBEditTxt: TextInputEditText
    private lateinit var ProfileEditDOBTxtLayout: TextInputLayout

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        supportActionBar?.title="Profile"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        ProfileLayout=findViewById(R.id.ViewProfile_layout)
        EditProfileLayout=findViewById(R.id.EditProfile_layout)
        EditProfileLayout.visibility=View.GONE
//        TODO: need to set condition where to check that details are empty or not
//        ProfileLayout.visibility= View.GONE
//        EditProfileLayout.visibility=View.VISIBLE
//        EditProfileBtn.visibility=View.GONE
//        supportActionBar?.title="Edit Profile"
//        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        EditProfileBtn=findViewById(R.id.ProfileEditBtn)
        EditProfileSaveBtn=findViewById(R.id.EditProfileSaveBtn)
        EditProfileCancleBtn=findViewById(R.id.EditProfileCancleBtn)
        UserNameText=findViewById(R.id.UserNameText)
        GenderText=findViewById(R.id.GenderText)
        DOBText=findViewById(R.id.DOBText)
        PhoneText=findViewById(R.id.PhoneText)
        AddressText=findViewById(R.id.AddressText)
        profileNameEditTxt=findViewById(R.id.profileNameEditTxt)
        ProfileEditGenderRadioGroup=findViewById(R.id.ProfileEditGenderRadioGroup)
        profileDOBEditTxt=findViewById(R.id.profileDOBEditTxt)
        ProfileEditDOBTxtLayout=findViewById(R.id.ProfileEditDOBTxtLayout)
        
        profileDOBEditTxt.addTextChangedListener(object : TextWatcher {
            private var current = ""
            private val ddmmyyyy = "DDMMYYYY"
            private val cal = Calendar.getInstance()

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                s?.let {
                    if (s.toString() != current) {
                        var clean = s.toString().replace("[^\\d.]|\\.".toRegex(), "")
                        val cleanC = current.replace("[^\\d.]|\\.".toRegex(), "")

                        val cl = clean.length
                        var sel = cl
                        for (i in 2 until cl.coerceAtMost(6) step 2) {
                            sel++
                        }

                        if (clean == cleanC) sel--

                        if (clean.length < 8) {
                            clean += ddmmyyyy.substring(clean.length)
                        } else {
                            var day = clean.substring(0, 2).toInt()
                            var mon = clean.substring(2, 4).toInt()
                            var year = clean.substring(4, 8).toInt()

                            mon = if (mon < 1) 1 else if (mon > 12) 12 else mon
                            cal.set(Calendar.MONTH, mon - 1)
                            cal.add(Calendar.YEAR,18)
                            val customyear=LocalDate.now()
                            year = year.coerceIn(1900, customyear.year-18 )
                            cal.set(Calendar.YEAR, year)

                            val maxDay = cal.getActualMaximum(Calendar.DATE)
                            day= if (day > maxDay) maxDay else day

                            clean = String.format("%02d%02d%02d",day,mon,year)
                        }

                        clean = String.format("%s/%s/%s", clean.substring(0, 2), clean.substring(2, 4), clean.substring(4, 8))
                        sel = sel.coerceAtLeast(0)
                        current = clean
                        profileDOBEditTxt.setText(current)
                        profileDOBEditTxt.setSelection(sel.coerceAtMost(current.length))
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun afterTextChanged(s: Editable?) {}
        })

        ProfileEditDOBTxtLayout.setEndIconOnClickListener {
            // on below line we are getting
            // the instance of our calendar.
            val c = Calendar.getInstance()
            c.add(Calendar.YEAR,-18)
            // on below line we are getting
            // our day, month and year.
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            // on below line we are creating a
            // variable for date picker dialog.
            val datePickerDialog = DatePickerDialog(
                // on below line we are passing context.
                this,{ view, dayOfMonth, monthOfYear,year ->
                    // on below line we are setting
                    // date to our edit text.
                    if (monthOfYear<10){

                         val dat = (year.toString() + "/0" + (monthOfYear + 1) + "/" + dayOfMonth)
                        profileDOBEditTxt.setText(dat)
                    }
                    else{
                        val dat = (year.toString() + "/" + (monthOfYear + 1) + "/" + dayOfMonth)
                        profileDOBEditTxt.setText(dat)
                    }
                },
                // on below line we are passing year, month
                // and day for the selected date in our date picker.
                year,month,day
            )
            datePickerDialog.datePicker.maxDate= c.timeInMillis
            // at last we are calling show
            // to display our date picker dialog.
            datePickerDialog.show()
        }
        ProfileEditGenderRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            val gender = findViewById<RadioButton>(checkedId).text.toString()
            Toast.makeText(this, gender, Toast.LENGTH_SHORT).show()
        }
        EditProfileBtn.setOnClickListener {
            ProfileLayout.visibility= View.GONE
            EditProfileLayout.visibility=View.VISIBLE
            EditProfileBtn.visibility=View.GONE
            supportActionBar?.title="Edit Profile"
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }
        EditProfileSaveBtn.setOnClickListener {
            ProfileLayout.visibility= View.VISIBLE
            EditProfileLayout.visibility=View.GONE
            EditProfileBtn.visibility=View.VISIBLE
            supportActionBar?.title="Profile"
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        EditProfileCancleBtn.setOnClickListener {
            ProfileLayout.visibility= View.VISIBLE
            EditProfileLayout.visibility=View.GONE
            EditProfileBtn.visibility=View.VISIBLE
            supportActionBar?.title="Profile"
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

    }
}