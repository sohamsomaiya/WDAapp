package com.example.wda

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.*
import android.util.Base64
import com.example.wda.api.User
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.time.LocalDate
import java.util.*

class ProfileActivity : AppCompatActivity() {
    private lateinit var ProfileLayout: LinearLayout
    private lateinit var EditProfileLayout: LinearLayout
    private lateinit var EditProfileBtn: ImageButton
    private lateinit var EditProfileSaveBtn: Button
    private lateinit var EditProfileCancleBtn: Button
    private lateinit var NameText: TextView
    private lateinit var GenderText: TextView
    private lateinit var DOBText: TextView
    private lateinit var PhoneText: TextView
    private lateinit var AddressText: TextView
    private lateinit var profileNameEditTxt: TextView
    private lateinit var ProfileEditGenderRadioGroup: RadioGroup
    private lateinit var profileDOBEditTxt: TextInputEditText
    private lateinit var profileContactEditTxt : TextInputEditText
    private lateinit var ProfileEditDOBTxtLayout: TextInputLayout
    private lateinit var EditProfileImageBtn: ImageView
    private lateinit var ProfileImage: ImageView
    private var imageUri: Uri? = null
    private lateinit var profilePincodeEditTxt: TextView
    private lateinit var profileStateEditTxt: TextView
    private lateinit var profileCityEditTxt: TextView
    private lateinit var profileAddressEditTxt: TextView
    private val pickimg = 100
    private var baseProfileImage=""

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        supportActionBar?.title = "Profile"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        ProfileLayout = findViewById(R.id.ViewProfile_layout)
        EditProfileLayout = findViewById(R.id.EditProfile_layout)
        EditProfileImageBtn = findViewById(R.id.EditProfileImageBtn)
        EditProfileLayout.visibility = View.GONE
        EditProfileImageBtn.visibility=View.GONE
//        TODO: need to set condition where to check that details are empty or not
//        ProfileLayout.visibility= View.GONE
//        EditProfileLayout.visibility=View.VISIBLE
//        EditProfileBtn.visibility=View.GONE
//        supportActionBar?.title="Edit Profile"
//        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        EditProfileBtn = findViewById(R.id.ProfileEditBtn)
        EditProfileSaveBtn = findViewById(R.id.EditProfileSaveBtn)
        EditProfileCancleBtn = findViewById(R.id.EditProfileCancleBtn)
        NameText = findViewById(R.id.NameText)
        GenderText = findViewById(R.id.GenderText)
        DOBText = findViewById(R.id.DOBText)
        PhoneText = findViewById(R.id.PhoneText)
        AddressText = findViewById(R.id.AddressText)
        profileNameEditTxt = findViewById(R.id.profileNameEditTxt)
        ProfileEditGenderRadioGroup = findViewById(R.id.ProfileEditGenderRadioGroup)
        profileDOBEditTxt = findViewById(R.id.profileDOBEditTxt)
        profileContactEditTxt=findViewById(R.id.ContactEditTxt)
        ProfileEditDOBTxtLayout = findViewById(R.id.ProfileEditDOBTxtLayout)
        profileAddressEditTxt = findViewById(R.id.profileAddressEditTxt)
        profileCityEditTxt = findViewById(R.id.profileCityEditTxt)
        profileStateEditTxt = findViewById(R.id.profileStateEditTxt)
        profilePincodeEditTxt = findViewById(R.id.profilePincodeEditTxt)
        var gender = ""
        val sh = getSharedPreferences("wda", MODE_PRIVATE)
        val userid = sh.getString("userId", "")
        val contact = sh.getString("contact","")
        //Toast.makeText(this, name, Toast.LENGTH_SHORT).show()
        ProfileImage = findViewById(R.id.ProfileImageView)
        val ContactNo = contact.toString()


//        NameText.text=name
        PhoneText.text="+91"+ContactNo


        CoroutineScope(Dispatchers.IO).launch {
            val userProfile = User.getUserProfile(ContactNo)
            try{
                if(userProfile.imageName!!.isNotBlank()){
                    User.downloadProfileImage(this@ProfileActivity, userProfile.imageName!!)
                }else{
                    ProfileImage.setBackgroundResource(R.drawable.contactbuttongrayicon)
                }
                withContext(Dispatchers.Main) {
                    if(userProfile.Gender!!.isNotBlank() || userProfile.Dob!!.isNotBlank()){
                        GenderText.text = userProfile.Gender
//                      radiobutton set to gender.
                        if (userProfile.Gender=="Male")
                        {
                            val RadioGenderMale=findViewById<RadioButton>(R.id.GenderMaleRbtn)
                            RadioGenderMale.isChecked=true
                        }
                        else if(userProfile.Gender=="Female")
                        {
                            val RadioGenderFemale=findViewById<RadioButton>(R.id.GenderFemaleRbtn)
                            RadioGenderFemale.isChecked=true
                        }
                        else{
                            val RadioGenderOther=findViewById<RadioButton>(R.id.GenderOtherRbtn)
                            RadioGenderOther.isChecked=true
                        }
                        Log.i("Messege From WithContext","hello.")
//                        profileAddressEditTxt.text=userProfile.Address
                        profileDOBEditTxt.text = Editable.Factory.getInstance().newEditable(userProfile.Dob)
                        DOBText.text = userProfile.Dob
                    }else{
                        GenderText.text="Gender"
//                        profileAddressEditTxt.text=profileAddressEditTxt.hint.toString()
                        profileDOBEditTxt.text = Editable.Factory.getInstance().newEditable("DD/MM/YYYY")
                        DOBText.text = "DOB"
                    }
                    AddressText.text = userProfile.Address +" "+ userProfile.City +" "+ userProfile.State +" "+ userProfile.Pincode
                    profileAddressEditTxt.text=userProfile.Address
                    profileCityEditTxt.text=userProfile.City
                    profileStateEditTxt.text=userProfile.State
                    profilePincodeEditTxt.text=userProfile.Pincode
                    PhoneText.text=userProfile.ContactNo
                    NameText.text=userProfile.Name
                    profileNameEditTxt.text= userProfile.Name
                    ProfileImage.setImageURI(Uri.parse("${externalCacheDir?.absolutePath}/userProfile/${userProfile.imageName}"))
                }
            }catch (ex:java.lang.Exception){
                Log.i("GET PROFILE ERROR",ex.message!!)
            }
        }
        profileDOBEditTxt.addTextChangedListener(object : TextWatcher {
            private var current = ""
            private val ddmmyyyy = "DD/MM/YYYY"
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
                            cal.add(Calendar.YEAR, 18)
                            val customyear = LocalDate.now()
                            year = year.coerceIn(1900, customyear.year - 18)
                            cal.set(Calendar.YEAR, year)

                            val maxDay = cal.getActualMaximum(Calendar.DATE)
                            day = if (day > maxDay) maxDay else day

                            clean = String.format("%02d%02d%02d", day, mon, year)
                        }

                        clean = String.format(
                            "%s/%s/%s",
                            clean.substring(0, 2),
                            clean.substring(2, 4),
                            clean.substring(4, 8)
                        )
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
            c.add(Calendar.YEAR, -18)
            // on below line we are getting
            // our day, month and year.
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            // on below line we are creating a
            // variable for date picker dialog.
            val datePickerDialog = DatePickerDialog(
                // on below line we are passing context.
                this, { view, dayOfMonth, monthOfYear, year ->
                    // on below line we are setting
                    // date to our edit text.
                    if (monthOfYear < 10) {

                        val dat = (year.toString() + "/0" + (monthOfYear + 1) + "/" + dayOfMonth)
                        profileDOBEditTxt.setText(dat)
                    } else {
                        val dat = (year.toString() + "/" + (monthOfYear + 1) + "/" + dayOfMonth)
                        profileDOBEditTxt.setText(dat)
                    }
                },
                // on below line we are passing year, month
                // and day for the selected date in our date picker.
                year, month, day
            )
            datePickerDialog.datePicker.maxDate = c.timeInMillis
            // at last we are calling show
            // to display our date picker dialog.
            datePickerDialog.show()
        }

        ProfileEditGenderRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            gender = findViewById<RadioButton>(checkedId).text.toString()
            //Toast.makeText(this, gender, Toast.LENGTH_SHORT).show()
        }
        EditProfileBtn.setOnClickListener {
            ProfileLayout.visibility = View.GONE
            EditProfileLayout.visibility = View.VISIBLE
            EditProfileBtn.visibility = View.GONE
            EditProfileImageBtn.visibility = View.VISIBLE
            supportActionBar?.title = "Edit Profile"
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            profileContactEditTxt.setText(ContactNo)

        }


        EditProfileSaveBtn.setOnClickListener {
            Log.i("Save button clicked","Saved")
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val updateUserProfile = User.updateUserProfile(
                        profileNameEditTxt.text.toString(),
                        gender,
                        profileContactEditTxt.text.toString(),
                        profileDOBEditTxt.text.toString(),
                        profileAddressEditTxt.text.toString(),
                        baseProfileImage,
                        profileCityEditTxt.text.toString(),
                        profileStateEditTxt.text.toString(),
                        profilePincodeEditTxt.text.toString()
                    )

                    withContext(Dispatchers.Main){
                        if(updateUserProfile.getBoolean("success")){
                            this@ProfileActivity.recreate()
                            Toast.makeText(this@ProfileActivity, updateUserProfile.getString("message"), Toast.LENGTH_SHORT).show()
                        }
                        else{
                            Toast.makeText(this@ProfileActivity, updateUserProfile.getString("message"), Toast.LENGTH_SHORT).show()
                        }
                    }
                }catch (ex:java.lang.Exception){
                    Log.i("ERROR IN UPDATE PROFILE",ex.message!!)
                }
            }

            ProfileLayout.visibility = View.VISIBLE
            EditProfileLayout.visibility = View.GONE
            EditProfileBtn.visibility = View.VISIBLE
            supportActionBar?.title = "Profile"
            EditProfileImageBtn.visibility = View.GONE
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        EditProfileImageBtn.setOnClickListener {
            // Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show()
            val gallery =
                Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickimg)
        }
        EditProfileCancleBtn.setOnClickListener {
            ProfileLayout.visibility = View.VISIBLE
            EditProfileLayout.visibility = View.GONE
            EditProfileBtn.visibility = View.VISIBLE
            EditProfileImageBtn.visibility = View.GONE
            supportActionBar?.title = "Profile"
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickimg && data != null && data.data != null) {
            imageUri=data.data!!
            ProfileImage.setImageURI(imageUri)

            val bitmap: Bitmap = MediaStore.Images.Media.getBitmap(contentResolver, data.data!!)
            val byteArrayOutputStream = ByteArrayOutputStream()

            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)

            val byteArray: ByteArray = byteArrayOutputStream.toByteArray()

            baseProfileImage = Base64.encodeToString(byteArray, Base64.DEFAULT)
        }
    }
}