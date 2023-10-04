package com.example.wda.api

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class User : AppCompatActivity() {

    companion object{
            val ipaddress="192.168.208.120:8000"

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
    }
}