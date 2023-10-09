package com.example.wda.api

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.model.Profile
import com.example.wda.model.Website
import org.json.JSONObject
import java.io.File
import java.io.FileOutputStream
import java.net.HttpURLConnection
import java.net.URL

class User : AppCompatActivity() {

    companion object{
        val ipaddress="172.16.9.235:8000"

        fun sendOtp(Name : String,Type : String,ContactNo : String): JSONObject {
            val jsonLogin=JSONObject()
            jsonLogin.put("name",Name)
            jsonLogin.put("type",Type)
            jsonLogin.put("contactNo",ContactNo)

            val jsonLoginResponse=jsonLogin.toString()

            val url =URL("http://${ipaddress}/wda/user/sendOtp")
            val httpconnection =(url.openConnection() as HttpURLConnection).apply {
                doInput=true
                doOutput=true
                requestMethod="POST"
                setRequestProperty("Content-Type","application/json")
            }
            try {
                val writeLoginDate =httpconnection.outputStream.bufferedWriter()
                writeLoginDate.write(jsonLoginResponse)
                writeLoginDate.flush()
                if (httpconnection.responseCode==HttpURLConnection.HTTP_OK){
                    val jsonResponse=httpconnection.inputStream.bufferedReader()
                    val jsonResponseLogin = jsonResponse.readText()
                    return JSONObject(jsonResponseLogin)
                }
            }catch (ex : Exception){
                Log.e("OTP Error",ex.message!!)

            }
            return null!!
        }

        fun verifyOtp(Name : String,Otp : String): JSONObject {
            val jsonOtp=JSONObject()

            jsonOtp.put("otpCode",Otp)
            jsonOtp.put("name",Name)

            val otpResponse = jsonOtp.toString()

            val url = URL("http://${ipaddress}/wda/user/verifyOtp")
            val httpconnection = (url.openConnection() as HttpURLConnection).apply {
                doInput=true
                doOutput=true
                requestMethod="POST"
                setRequestProperty("Content-Type","application/json")
            }
            try {
                val writeOtp = httpconnection.outputStream.bufferedWriter()
                writeOtp.write(otpResponse)
                writeOtp.flush()
                if (httpconnection.responseCode==HttpURLConnection.HTTP_OK){
                    val jsonResponse = httpconnection.inputStream.bufferedReader()
                    val readOtp = jsonResponse.readText()
                    return JSONObject(readOtp)
                }
            }catch (ex : Exception){
                Log.e("Otp Verification",ex.message!!)
            }
            return null!!
        }

        fun registerUser(name:String,Password : String, ConfirmPwd : String): JSONObject {
            val jsonRegister = JSONObject()
            jsonRegister.put("name",name)
            jsonRegister.put("password",Password)
            jsonRegister.put("confirmpassword",ConfirmPwd)

            val jsonRegisterResponse = jsonRegister.toString()
            val url = URL("http://${ipaddress}/wda/user/registerUser")
            val httpconnection = (url.openConnection() as HttpURLConnection).apply {
                doInput=true
                doOutput=true
                requestMethod="POST"
                setRequestProperty("Content-Type","application/json")
            }

            try {
                val writeUserRegister = httpconnection.outputStream.bufferedWriter()
                writeUserRegister.write(jsonRegisterResponse)
                writeUserRegister.flush()
                if (httpconnection.responseCode==HttpURLConnection.HTTP_OK){
                    val jsonResponse = httpconnection.inputStream.bufferedReader()
                    val readUser = jsonResponse.readText()
                    return JSONObject(readUser)
                }
            }catch (ex : Exception){
                Log.e("Registration Error",ex.message!!)
            }
            return null!!

        }

        fun login(ContactNo: String, Password : String,type:String): JSONObject {
            val jsonverify = JSONObject()
            jsonverify.put("contactNumber",ContactNo)
            jsonverify.put("password",Password)
            jsonverify.put("type",type)
            val jsonVerifyResponse = jsonverify.toString()
            val url = URL("http://${ipaddress}/wda/user/login")
            val httpconnection = (url.openConnection() as HttpURLConnection).apply {
                doInput=true
                doOutput=true
                requestMethod="POST"
                setRequestProperty("Content-Type","application/json")
            }

            try {
                val writeUser = httpconnection.outputStream.bufferedWriter()
                writeUser.write(jsonVerifyResponse)
                writeUser.flush()
                if (httpconnection.responseCode==HttpURLConnection.HTTP_OK){
                    val jsonUserResponse = httpconnection.inputStream.bufferedReader()
                    val readuserResponse = jsonUserResponse.readText()
                    return JSONObject(readuserResponse)
                }
            }catch (ex : Exception){
                Log.e("Login Error",ex.message!!)
            }
            return null!!
        }

        fun updateUserProfile(Name : String,Gender : String,Contact : String, Dob : String,Address : String,ImagePath : String,City : String, State : String, Pincode : String): JSONObject {
            val jsonProfile = JSONObject()
            jsonProfile.put("name",Name)
            jsonProfile.put("gender",Gender)
            jsonProfile.put("contactNumber",Contact)
            jsonProfile.put("dob",Dob)
            jsonProfile.put("address",Address)
            jsonProfile.put("city",City)
            jsonProfile.put("state",State)
            jsonProfile.put("pincode",Pincode)
            jsonProfile.put("profileImagePath",ImagePath)
            val jsonProfileResponse = jsonProfile.toString()

            val url = URL("http://${ipaddress}/wda/user/updateUserProfile")
            val httpconnection= (url.openConnection() as HttpURLConnection).apply {
                doInput=true
                doOutput=true
                requestMethod="PUT"
                setRequestProperty("Content-Type","application/json")
            }
            try {
                val writeUserProfile = httpconnection.outputStream.bufferedWriter()
                writeUserProfile.write(jsonProfileResponse)
                writeUserProfile.flush()
                if (httpconnection.responseCode==HttpURLConnection.HTTP_OK){
                    val jsonProfile = httpconnection.inputStream.bufferedReader()
                    val readUserProfile = jsonProfile.readText()
                    return JSONObject(readUserProfile)
                }
            }catch (ex : Exception){
                Log.e("Profile Update Error",ex.message!!)
            }
            return null!!
        }

        fun getUserProfile(contactNo:String): Profile {
            val url = URL("http://${ipaddress}/wda/user/getUserProfile/${contactNo}")
            val httpConnection = (url.openConnection() as HttpURLConnection).apply {
                doInput = true
                requestMethod = "GET"
                setChunkedStreamingMode(0)
            }
            try {
                if (httpConnection.responseCode == HttpURLConnection.HTTP_OK) {
                    val reader = httpConnection.inputStream.bufferedReader()
                    val responseJsonString = JSONObject(reader.readText())
                    val responseProfile=responseJsonString.getJSONObject("userProfile")
                    return Profile(
                        responseProfile.getString("Name"),
                        responseProfile.getString("Type"),
                        responseProfile.getString("ContactNo"),
                        responseProfile.optString("Gender"),
                        responseProfile.optString("DOB"),
                        responseProfile.optString("Address"),
                        responseProfile.optString("City"),
                        responseProfile.optString("State"),
                        responseProfile.optString("Pincode"),
                        responseProfile.optString("Image")
                    )
                }
            } catch (ex: Exception) {
                Log.e("Get Profile", ex.message!!)
            }
            return null!!
        }

        fun downloadProfileImage(context: Context, imageName: String)
        {

            val url = URL("http://${ipaddress}/wda/userProfile/${imageName}")

            val connection = (url.openConnection() as HttpURLConnection).apply {
                doInput=true
                requestMethod="GET"
            }

            try
            {
                val cacheDirPath = context.externalCacheDir!!.absolutePath
                val imageDirPath = "${cacheDirPath}/userProfile"
                val imagepath = File(imageDirPath)

                if (!imagepath.exists())
                {
                    imagepath.mkdirs()
                }

                val imageSavePath =
                    FileOutputStream("${imagepath.absolutePath}/${imageName}")

                Log.i("ImagePAth","${imagepath.absolutePath}/${imageName}")

                connection.connect()
                if (connection.responseCode == HttpURLConnection.HTTP_OK)
                {
                    val bitmap = BitmapFactory.decodeStream(connection.inputStream)
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, imageSavePath)
                }
            }
            catch (ex: Exception)
            {
                Log.e("ProductdownloadImage", ex.message!!)
            }
        }

        fun addImages(businessName:String,imageID:String?,imagePath:String) : JSONObject{
            val jsonLogin = JSONObject()
            jsonLogin.put("businessName",businessName)
            jsonLogin.put("imageID",imageID)
            jsonLogin.put("imagePath",imagePath)

            val jsonLoginResponse = jsonLogin.toString()

            val url = URL("http://${ipaddress}/wda/business/addImages")
            val httpconnection = (url.openConnection() as HttpURLConnection).apply {
                doInput=true
                doOutput=true
                requestMethod="POST"
                setRequestProperty("Content-Type","application/json")
            }
            try {
                val writeLogindata= httpconnection.outputStream.bufferedWriter()
                writeLogindata.write(jsonLoginResponse)
                writeLogindata.flush()
                if (httpconnection.responseCode==HttpURLConnection.HTTP_OK){
                    val jsonResponse = httpconnection.inputStream.bufferedReader()
                    val jsonResponseLogin=jsonResponse.readText()
                    return JSONObject(jsonResponseLogin)
                }
            }catch (ex : Exception){
                Log.e("Login Error",ex.message!!)
            }
            return null!!

        }

        fun websiteRegister(userId:String,websiteName:String,htmlFile:String,websiteType:String,dateOfIntegration:String,corporateIdentificationNo:String,taxDeductionAccNo:String,goodsServiceTax:String) : JSONObject{
            val jsonLogin = JSONObject()
            jsonLogin.put("htmlFile",htmlFile)
            jsonLogin.put("webSiteName",websiteName)
            jsonLogin.put("websiteType",websiteType)
            jsonLogin.put("dateOfIntegration",dateOfIntegration)
            jsonLogin.put("corporateIdentificationNo",corporateIdentificationNo)
            jsonLogin.put("taxDeductionAccNo",taxDeductionAccNo)
            jsonLogin.put("goodsServiceTax",goodsServiceTax)
            jsonLogin.put("userId",userId)

            val jsonLoginResponse = jsonLogin.toString()

            val url = URL("http://${ipaddress}/wda/business/websiteRegister")
            val httpconnection = (url.openConnection() as HttpURLConnection).apply {
                doInput=true
                doOutput=true
                requestMethod="POST"
                setRequestProperty("Content-Type","application/json")
            }
            try {
                val writeLogindata= httpconnection.outputStream.bufferedWriter()
                writeLogindata.write(jsonLoginResponse)
                writeLogindata.flush()
                if (httpconnection.responseCode==HttpURLConnection.HTTP_OK){
                    val jsonResponse = httpconnection.inputStream.bufferedReader()
                    val jsonResponseLogin=jsonResponse.readText()
                    return JSONObject(jsonResponseLogin)
                }
            }catch (ex : Exception){
                Log.e("Login Error",ex.message!!)
            }
            return null!!

        }

        fun getWebsiteDetails(userId : String) : Array<Website>{

            val WebsiteArrayList = arrayListOf<Website>()

            val url = URL("http://${ipaddress}/wda/user/getUserWebsites/${userId}")
            val httpConnection = (url.openConnection() as HttpURLConnection).apply {
                doInput = true
                requestMethod = "GET"
                setChunkedStreamingMode(0)
            }
            try {
                if (httpConnection.responseCode == HttpURLConnection.HTTP_OK) {
                    val websiteReader = httpConnection.inputStream.bufferedReader()
                    val responseWebsiteString = JSONObject(websiteReader.readText())
                    val responseWebsite = responseWebsiteString.getJSONArray("websiteDetails")
                    var i = 0
                    while (i < responseWebsite.length()) {
                        val websiteData = responseWebsite.getJSONObject(i)
                        val Websites = Website(
                            websiteData.getString("_id"),
                            websiteData.getString("websiteName"),
                            websiteData.getString("websiteType"),
                            websiteData.getString("dateOfIncorporation"),
                            websiteData.getString("corporateIdentificationNo"),
                            websiteData.getString("taxDeductionAccNo"),
                            websiteData.getString("domainName")
                        )
                        WebsiteArrayList.add(Websites)
                        i++
                    }
                    return WebsiteArrayList.toTypedArray()
                }
            }
            catch (ex : Exception){
                Log.e("Website Data",ex.message!!)
            }
            return null!!
        }
    }

    fun addImages(businessName:String, imageID:String?, imagePath:String) : JSONObject{
        val jsonLogin = JSONObject()
        jsonLogin.put("businessName",businessName)
        jsonLogin.put("imageID",imageID)
        jsonLogin.put("imagePath",imagePath)

        val jsonLoginResponse = jsonLogin.toString()

        val url = URL("http://${ipaddress}/wda/business/addImages")
        val httpconnection = (url.openConnection() as HttpURLConnection).apply {
            doInput=true
            doOutput=true
            requestMethod="POST"
            setRequestProperty("Content-Type","application/json")
        }
        try {
            val writeLogindata= httpconnection.outputStream.bufferedWriter()
            writeLogindata.write(jsonLoginResponse)
            writeLogindata.flush()
            if (httpconnection.responseCode==HttpURLConnection.HTTP_OK){
                val jsonResponse = httpconnection.inputStream.bufferedReader()
                val jsonResponseLogin=jsonResponse.readText()
                return JSONObject(jsonResponseLogin)
            }
        }catch (ex : Exception){
            Log.e("Login Error",ex.message!!)
        }
        return null!!

    }
}