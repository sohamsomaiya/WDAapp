package com.example.wda

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.wda.api.User
import com.example.wda.api.User.Companion.ipaddress
import com.example.wda.databinding.ActivityEditWebBinding
import com.example.wda.databinding.FragmentBottom2Binding
import com.example.wda.databinding.FragmentBottomBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.*
import java.io.ByteArrayOutputStream
import java.util.zip.GZIPOutputStream

class ModalBottomSheet : BottomSheetDialogFragment() {
    companion object{
        const val TAG = "ModalBottomSheet"
    }

    private lateinit var binding: FragmentBottomBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ):  View? {
        binding = FragmentBottomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize and customize your bottom sheet dialog here
//        binding.textView.text = "This is a bottom sheet dialog"
        binding.textFont1.setOnClickListener {
            binding.textFont1.isChecked = true
            binding.textFont2.isChecked = false
            binding.textFont3.isChecked = false
            binding.textFont4.isChecked = false
            binding.textFont5.isChecked = false
            binding.textFont1.strokeWidth=3
            binding.textFont2.strokeWidth=0
            binding.textFont3.strokeWidth=0
            binding.textFont4.strokeWidth=0
            binding.textFont5.strokeWidth=0

        }
        binding.textFont2.setOnClickListener {
            binding.textFont1.isChecked = false
            binding.textFont2.isChecked = true
            binding.textFont3.isChecked = false
            binding.textFont4.isChecked = false
            binding.textFont5.isChecked = false
            binding.textFont1.strokeWidth=0
            binding.textFont2.strokeWidth=3
            binding.textFont3.strokeWidth=0
            binding.textFont4.strokeWidth=0
            binding.textFont5.strokeWidth=0
        }
        binding.textFont3.setOnClickListener {
            binding.textFont1.isChecked = false
            binding.textFont2.isChecked = false
            binding.textFont3.isChecked = true
            binding.textFont4.isChecked = false
            binding.textFont5.isChecked = false
            binding.textFont1.strokeWidth=0
            binding.textFont2.strokeWidth=0
            binding.textFont3.strokeWidth=3
            binding.textFont4.strokeWidth=0
            binding.textFont5.strokeWidth=0
        }
        binding.textFont4.setOnClickListener {
            binding.textFont1.isChecked = false
            binding.textFont2.isChecked = false
            binding.textFont3.isChecked = false
            binding.textFont4.isChecked = true
            binding.textFont5.isChecked = false
            binding.textFont1.strokeWidth=0
            binding.textFont2.strokeWidth=0
            binding.textFont3.strokeWidth=0
            binding.textFont4.strokeWidth=3
            binding.textFont5.strokeWidth=0
        }
        binding.textFont5.setOnClickListener {
            binding.textFont1.isChecked = false
            binding.textFont2.isChecked = false
            binding.textFont3.isChecked = false
            binding.textFont4.isChecked = false
            binding.textFont5.isChecked = true
            binding.textFont1.strokeWidth=0
            binding.textFont2.strokeWidth=0
            binding.textFont3.strokeWidth=0
            binding.textFont4.strokeWidth=0
            binding.textFont5.strokeWidth=3
        }
        // Handle interactions or add more views as needed
        binding.closeButton.setOnClickListener {
            dismiss() // Close the bottom sheet dialog
        }
    }

}

class ModalBottomSheet2 : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentBottom2Binding
    companion object {
        const val TAG = "ModalBottomSheet"
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ):  View? {
        binding = FragmentBottom2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize and customize your bottom sheet dialog here
//        binding.textView.text = "This is a bottom sheet dialog"
        binding.colorPalet1.setOnClickListener {
            binding.colorPalet1.isChecked = true
            binding.colorPalet2.isChecked = false
            binding.colorPalet3.isChecked = false
            binding.colorPalet4.isChecked = false
            binding.colorPalet5.isChecked = false
            binding.colorPalet1.strokeWidth=3
            binding.colorPalet2.strokeWidth=0
            binding.colorPalet3.strokeWidth=0
            binding.colorPalet4.strokeWidth=0
            binding.colorPalet5.strokeWidth=0
        }
        binding.colorPalet2.setOnClickListener {
            binding.colorPalet1.isChecked = false
            binding.colorPalet2.isChecked = true
            binding.colorPalet3.isChecked = false
            binding.colorPalet4.isChecked = false
            binding.colorPalet5.isChecked = false
            binding.colorPalet1.strokeWidth=0
            binding.colorPalet2.strokeWidth=3
            binding.colorPalet3.strokeWidth=0
            binding.colorPalet4.strokeWidth=0
            binding.colorPalet5.strokeWidth=0
        }
        binding.colorPalet3.setOnClickListener {
            binding.colorPalet1.isChecked = false
            binding.colorPalet2.isChecked = false
            binding.colorPalet3.isChecked = true
            binding.colorPalet4.isChecked = false
            binding.colorPalet5.isChecked = false

            binding.colorPalet1.strokeWidth=0
            binding.colorPalet2.strokeWidth=0
            binding.colorPalet3.strokeWidth=3
            binding.colorPalet4.strokeWidth=0
            binding.colorPalet5.strokeWidth=0
        }
        binding.colorPalet4.setOnClickListener {
            binding.colorPalet1.isChecked = false
            binding.colorPalet2.isChecked = false
            binding.colorPalet3.isChecked = false
            binding.colorPalet4.isChecked = true
            binding.colorPalet5.isChecked = false
            binding.colorPalet1.strokeWidth=0
            binding.colorPalet2.strokeWidth=0
            binding.colorPalet3.strokeWidth=0
            binding.colorPalet4.strokeWidth=3
            binding.colorPalet5.strokeWidth=0
        }
        binding.colorPalet5.setOnClickListener {
            binding.colorPalet1.isChecked = false
            binding.colorPalet2.isChecked = false
            binding.colorPalet3.isChecked = false
            binding.colorPalet4.isChecked = false
            binding.colorPalet5.isChecked = true
            binding.colorPalet1.strokeWidth=0
            binding.colorPalet2.strokeWidth=0
            binding.colorPalet3.strokeWidth=0
            binding.colorPalet4.strokeWidth=0
            binding.colorPalet5.strokeWidth=3
        }
        // Handle interactions or add more views as needed
        binding.closeButton.setOnClickListener {
            dismiss() // Close the bottom sheet dialog
        }
    }


}

class EditWebActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditWebBinding
    private lateinit var web:WebView
    private lateinit var printLayout: TextView
    private lateinit var imgView:ImageView
    private var imageUri:Uri?=null
    private lateinit var btn:Button
    companion object{
        private var touchedImageId: String? = null
        private const val FILE_PICKER_REQUEST_CODE = 1
    }
    @SuppressLint("JavascriptInterface")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityEditWebBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btn = binding.imagePathBtn

        web = binding.webView
        printLayout=binding.layoutid
        web.webViewClient = WebViewClient()
        web.webChromeClient = WebChromeClient()
        web.settings.javaScriptEnabled = true
        web.settings.setSupportMultipleWindows(true)
        web.settings.domStorageEnabled = true
        web.settings.cacheMode = WebSettings.LOAD_NO_CACHE
        web.settings.setSupportZoom(true)
        web.settings.builtInZoomControls = true
        web.settings.displayZoomControls = false
        web.settings.loadWithOverviewMode = true
        web.settings.useWideViewPort = true
        web.webViewClient = MyWebViewClient()

        web.addJavascriptInterface(this, "AndroidInterface")
//        web.loadDataWithBaseURL(null, getHtmlContent(), "text/html", "UTF-8", null)
        val baseUrl = "http://${ipaddress}/wda/template3.html"
        val uri = Uri.parse(baseUrl)
            .buildUpon()
            .appendQueryParameter("ipaddress", ipaddress)
//            .appendQueryParameter("param2", variable2)
//            .appendQueryParameter("param3", variable3)
            .build()
        web.loadUrl(uri.toString())
        web.webChromeClient = WebChromeClient()

        web.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                btn.isEnabled = true
            }
        }
        web.webViewClient = object : WebViewClient() {
            override fun onReceivedError(view: WebView?, errorCode: Int, description: String?, failingUrl: String?) {
                super.onReceivedError(view, errorCode, description, failingUrl)
                Log.e("WebView Error", "Error Code: $errorCode, Description: $description")
            }
        }
        btn.setOnClickListener {
            web.evaluateJavascript(
                "(function() { return document.documentElement.outerHTML; })();"
            ) { content ->
                CoroutineScope(Dispatchers.IO).launch {
                    withContext(Dispatchers.Main){
                        val cleanedContent = content.trim().removePrefix("\"").removeSuffix("\"")
                        val cleanedHtml = cleanedContent.replace("\\u003C", "<")
                            .replace("\\n", "")
                            .replace("\\", "")

                        val compressedHtml = compressGzip(cleanedHtml)
                        val base64Html = Base64.encodeToString(compressedHtml,Base64.DEFAULT)
                        withContext(Dispatchers.IO){
                            User.websiteRegister("65133ab2812fd50cec6e1760","Darshi And TechnoSoft",base64Html,"Organisational","16-09-2023","6484984894","4684646354","6846164836")
                        }
                    }
                }
            }

        }

        // Button click listener to show the bottom sheet dialog
        binding.EditFontButton.setOnClickListener {
            val modalBottomSheet = ModalBottomSheet()
            modalBottomSheet.show(supportFragmentManager, ModalBottomSheet.TAG)
        }
        binding.EditColorButton.setOnClickListener {
            val modalBottomSheet2 = ModalBottomSheet2()
            modalBottomSheet2.show(supportFragmentManager, ModalBottomSheet2.TAG)
        }
    }
    private inner class MyWebViewClient : WebViewClient()

    @JavascriptInterface
    fun showFileChooser(id: String) {
        touchedImageId=id
        showCustomFileChooser()
    }

    fun compressGzip(input: String): ByteArray {
        val byteArrayOutputStream = ByteArrayOutputStream()
        GZIPOutputStream(byteArrayOutputStream).bufferedWriter().use { writer ->
            writer.write(input)
        }
        return byteArrayOutputStream.toByteArray()
    }

    private fun showCustomFileChooser() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(intent, FILE_PICKER_REQUEST_CODE)
    }

    @OptIn(DelicateCoroutinesApi::class)
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode!= RESULT_CANCELED){
            imageUri=data?.data!!

            val bitmap: Bitmap = MediaStore.Images.Media.getBitmap(contentResolver, imageUri)
            val byteArrayOutputStream = ByteArrayOutputStream()

            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)

            val byteArray: ByteArray = byteArrayOutputStream.toByteArray()

            val base64Image = Base64.encodeToString(byteArray, Base64.DEFAULT)

            CoroutineScope(Dispatchers.IO).launch {
                var imageJSONData=User.addImages("Vaibhav Patel", touchedImageId,base64Image)
//                    user.downloadsingleImage(this@MainActivity,userProfile.Image)
//                    withContext(Dispatchers.Main){
//                        imgView.setImageURI(Uri.parse("${externalCacheDir?.absolutePath}/userProfile/${userProfile.Image}"))
//                    }
//                    user.updateUserProfile("Vaibhav","male","8155801818","28-12-2001","Janta Society",base64Image)
//                    val imageJSONData= user.updateUserProfile("VaibhavEnterprise","","8155801818","","",base64Image)
                val jsCode = "setImages('$imageJSONData');"

                GlobalScope.launch(Dispatchers.Main) {
                    web.evaluateJavascript(jsCode, null)
                }
            }
        }
    }
}
private fun getHtmlContent():String
{ return """ 
  
  <!DOCTYPE html>
  <html lang="en">
    <head>
      <meta charset="UTF-8" />
      <meta http-equiv="X-UA-Compatible" content="IE=edge" />
      <meta name="viewport" content="width=device-width, initial-scale=1.0" />
      <title contenteditable="true">We Care</title>

      <script>
        function chooseFile() {
          AndroidInterface.showFileChooser(event.target.id);
        }
        function yourFileUploadHandler(filePaths) {
          console.log(filePaths);
        }
      </script>

      <!---- Font Awesome Cdn Linkage ------>
      <link
        rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
      />

      <!------- Favicon Linkage --------->
      <link rel="shortcut icon" href="fav.png" type="x-icon" />
    </head>

    <style>
      @import url("https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700;900&display=swap");

      * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
        text-decoration: none;
        list-style: none;
        scroll-behavior: smooth;
        /* font-family: sans-serif; */
      }

      /* ::-webkit-scrollbar-track {
      visibility: hidden;
    } */
      ::-webkit-scrollbar {
        width: 0.5em; /* Adjust the width as needed */
      }
      :root {
        --body: #fff;
      }

      .editable {
        position: relative;
      }

      .editable:focus .counter {
        display: block;
        position: relative;
        right: 20px; /* Adjust the right positioning as needed */
        bottom: -20px; /* Adjust the bottom positioning as needed */
        color: #777;
      }

      .counter {
        position: absolute;
        right: 20px;
        /* bottom: -20px; */
        color: #777;
        /* Adjust color as needed */
        display: none;
      }

      [contenteditable="true"] {
        /* outline: none; */
        resize: none;
        overflow: hidden;
        border: #777 1px;
        margin-block: 5px;
      }

      [contenteditable="true"]:empty::before {
        content: "Click to start typing";
        color: #aaa;
      }

      section {
        margin-top: 80px;
      }

      hr {
        width: 90vw;
        margin: 70px auto;
        border: 0.5px solid #880808;
      }

      body {
        font-family: #ffff;
        background-color: var(--body);
      }

      img {
        pointer-events: auto;
      }

      header {
        background-color: #ffff;
        position: fixed;
        width: 100vw;
        top: 0;
        left: 0;
        /* min-height: 50px; */
        box-shadow: 0 2px 20px 0 rgba(0, 0, 0, 0.2);
        z-index: 100;
        /* position: relative; */
      }

      .heading {
        font-size: 35px;
        color: #c91c1c;
        line-height: 50px;
        font-weight: 700;
        text-transform: uppercase;
        text-align: center;
        margin-bottom: 30px;
      }

      nav.navigation {
        /* display: flex; */
        /* justify-content: space-between;
      align-items: center; */
        /* width: 100%; */
        margin: 0 auto;
        padding: 10px 0;
        float: right;
      }

      .logo {
        height: auto;
        width: 10%;
        padding: 5px;
        position: fixed;
        margin-left: 20px;
        /* display: grid; */
      }

      .logo img {
        object-fit: fill;
        width: 30px;
        height: 30px;
        aspect-ratio: 3/4;
      }

      nav.navigation .nav-list ul li {
        display: inline-block;
        margin: 0 20px;
      }

      nav.navigation .nav-list ul li a {
        font-size: 18px;
        transition: all 0.5s ease;
        color: #c91c1cc7;
      }

      /* nav.navigation .logo img{
       object-fit: contain;
       width: 80px;
       height: 60px;

      } */
      #toogle {
        display: none;
      }

      @media (max-width: 600px) {
        header {
          min-height: 70px;
          /* height: ; */
          /* position: fixed; */
        }

        .logo {
          position: absolute;
          left: 45%;
          width: 80%;
          height: 80%;
        }

        .logo img {
          right: 42px;
          /* top: 70px; */
          position: relative;
          object-fit: contain;
          width: 80px;
          height: 60px;
          /* text-align: center; */
        }

        nav.navigation .nav-list {
          margin: 8px auto;
          position: relative;
          /* align-items: center; */
          right: 30px;
        }

        nav.navigation .nav-list ul {
          display: none;
          /* background-color: gray; */
          justify-content: center;
          align-items: center;
          flex-direction: column;
          width: 100%;
          height: 100vh;
          /* overflow-y: visible; */
          /* scroll-behavior: smooth; */
          /* gap: 10px; */
        }

        .tbox {
          height: 50px;
          width: 50px;
          cursor: pointer;

          position: absolute;
          z-index: 2;
          background-color: transparent;
        }

        #toogle {
          height: 2px;
          width: 30px;
          position: absolute;
          top: 30px;
          left: 20px;
          background-color: #c91c1c;
          display: block;
          cursor: pointer;
        }

        #toogle::before {
          content: "";
          height: 2px;
          width: 30px;
          position: absolute;
          top: 10px;
          left: 0px;
          background-color: #c91c1c;
        }

        #toogle::after {
          content: "";
          height: 2px;
          width: 30px;
          position: absolute;
          bottom: 10px;
          left: 0px;
          background-color: #c91c1c;
        }

        #toogle.active {
          height: 0;
          width: 0;
        }

        #toogle.active::before {
          content: "";
          transform: rotate(45deg);
          top: 0px;
          left: 0px;
        }

        #toogle.active::after {
          content: "";
          transform: rotate(-45deg);
          bottom: -2px;
          left: 0px;
        }

        nav.navigation:active {
          overflow: hidden;
          scroll-behavior: none;
          background-position: fixed;
          display: fixed;
        }

        nav.navigation .nav-list.active ul {
          display: flex;
          flex-direction: column;
          flex-wrap: wrap;
          padding: 10px;
          right: 70px;
          top: -69px;
          position: relative;
          width: 20%;
        }

        nav.navigation .nav-list.active ul li {
          margin: 20px 0;
          color: #f2f2f2;
          width: 100px;
        }
      }

      section.home {
        min-height: 100vh;
        width: 90vw;
        margin: 0 auto;
        display: flex;
        justify-content: center;
        align-items: center;
        background-color: var(--body);
      }

      section.home .intro {
        width: 50%;
      }

      section.home .intro p {
        font-size: 20px;
        margin: 1.25rem 0rem;
        color: #373738;
        line-height: 1.5;
      }

      section.home .intro h1 {
        font-size: 35px;
        color: #c91c1c;
        line-height: 50px;
        font-weight: 700;
        text-transform: uppercase;
      }

      section.home .intro span {
        background-color: #c91c1c;
        margin-right: 20px;
        cursor: pointer;
        font-size: 1rem;
        padding: 0.375rem 1.25rem;
        transition: ease-in-out 0.7s;
        border: 1px solid transparent;
      }

      section.home .intro span a {
        color: var(--body);
      }

      /* section.home .intro span:hover {
                background-color: var(--body);
                border-color: #c91c1c;
              }

              section.home .intro span:hover a {
                color: #c91c1c;
              } */

      section.home .home-img {
        width: 50%;
        object-fit: cover;
        display: flex;
        justify-content: center;
        align-items: center;
      }

      section.home .home-img img {
        width: 70%;
        /* animation: anime 6s infinite linear; */
      }

      @media (max-width: 650px) {
        section.home {
          flex-direction: column;
          margin-top: 80px;
        }

        section.home .intro,
        section.home .home-img {
          width: 100%;
          margin-top: 50px;
        }
      }

      .about {
        margin-bottom: 30px;
        min-height: 100vh;
      }

      .about-container {
        display: flex;
        justify-content: center;
        align-items: center;
        width: 90vw;
      }

      .about-container .img {
        width: 60%;
        object-fit: cover;
        display: flex;
        justify-content: center;
        align-items: center;
      }

      .about-container .img img {
        width: 90%;
        animation: anime 6s infinite linear;
      }

      .about-container .text {
        width: 40%;
      }

      .about-container .text p {
        margin: 20px 0px;
        font-size: 20px;
        color: #373738;
        line-height: 30px;
        font-weight: 400;
      }

      @media (max-width: 650px) {
        .about-container {
          flex-direction: column;
          margin-top: 80px;
          width: 100%;
        }

        .about-container .text,
        .about-container .img {
          width: 100%;
        }

        .about-container .text p {
          padding: 20px;
        }
      }

      .services {
        margin-bottom: 30px;
      }

      .services .service-container {
        display: grid;
        grid-template-columns: repeat(4, 1fr);
        grid-gap: 20px;
        width: 90vw;
        margin: 30px auto;
      }

      .services .service-container .box {
        margin: 20px 0px;
        padding: 15px 18px 20px 15px;
        background: #f2f2f2;
        text-align: center;
        box-shadow: 0px 3px 5px 0px rgb(0 0 0 / 10%);
        cursor: pointer;
        transition: all 0.3s ease-in-out;
        /* justify-content: center; */
      }

      .services .service-container .box i {
        color: #880808;
        font-size: 40px;
        margin: 10px;
      }

      .services .service-container .box h3 {
        color: #854054;
        text-transform: uppercase;
        padding: 10px 0px;
        font-size: 20px;
        font-weight: 500;
        margin: 1.25rem 0rem;
        width: 250px;
      }

      .services .service-container .box p {
        font-size: 15px;
        line-height: 24px;
        font-weight: 400;
        color: #373738;
        margin: 1.25rem 0rem;
        width: 250px;
      }

      @media (max-width: 910px) {
        .services .service-container {
          grid-template-columns: repeat(2, 1fr);
          /* width: 80vw; */
        }

        .services .service-container .box h3 {
          color: #854054;
          text-transform: uppercase;
          padding: 10px 0px;
          font-size: 20px;
          font-weight: 500;
          margin: 1.25rem;
          width: 250px;
        }

        .services .service-container .box p {
          font-size: 15px;
          line-height: 24px;
          font-weight: 400;
          color: #373738;
          margin: 1.25rem;
          width: 250px;
        }
      }

      @media (max-width: 700px) {
        .services .service-container {
          grid-template-columns: repeat(1, 1fr);
          width: 75vw;
        }

        .services .service-container .box h3 {
          color: #854054;
          text-transform: uppercase;
          padding: 10px 0px;
          font-size: 20px;
          font-weight: 500;
          margin: 1.25rem;
          width: 250px;
        }

        .services .service-container .box p {
          font-size: 15px;
          line-height: 24px;
          font-weight: 400;
          color: #373738;
          margin: 1.25rem;
          width: 250px;
        }
      }

      @media (max-width: 400px) {
        .services .service-container {
          grid-template-columns: repeat(1, 1fr);
          width: 90vw;
        }

        .services .service-container .box h3 {
          color: #854054;
          text-transform: uppercase;
          padding: 10px 0px;
          font-size: 20px;
          font-weight: 500;
          margin: 1.25rem;
          width: 250px;
        }

        .services .service-container .box p {
          font-size: 15px;
          line-height: 24px;
          font-weight: 400;
          color: #373738;
          margin: 1.25rem;
          width: 250px;
        }
      }

      .gallery-img {
        width: 90vw;
        margin: 30px auto;
      }

      .gbox {
        /* display: grid;
                    grid-gap: 20px;
                    grid-template-columns: repeat(3, 1fr); */
        display: flex;
        /* align-content: center; */
        align-items: center;
        flex-wrap: wrap;
        justify-content: center;
        margin: 0 auto;
      }

      div.gallery {
        margin: 15px;
        border: 1px solid #ccc;
        width: 350px;
      }

      div.subContainer {
        width: 80%;
        display: flex;
        align-self: center;
        position: relative;
        justify-content: center;
        padding: 5px;
      }

      div.subContainer img {
        width: 100%;
        height: 80%;
        aspect-ratio: 3/2;
        object-fit: fill;
        left: 33px;
        position: relative;
      }

      div.desc {
        padding: 15px;
        text-align: center;
        margin: 1.2rem;
        width: 250px;
        position: relative;
        left: 30px;
      }

      @media (max-width: 412px) {
        div.gallery,
        div.gallery img {
          width: 100%;
        }

        div.desc {
          padding: 15px;
          text-align: center;
          margin: 0.25rem 0rem;
          width: 250px;
          position: relative;

          left: 32px;
        }
      }

      footer {
        background: #db1e1e70;
        height: auto;
        width: 100%;
        padding-top: 40px;
        color: #c91c1c;
      }

      footer .footer-content {
        display: flex;
        align-items: center;
        justify-content: center;
        flex-direction: column;
        text-align: center;
      }

      footer .footer-content p {
        max-width: 30ch;
        margin: 10px auto;
        line-height: 28px;
        font-size: 14px;
      }

      footer .footer-bottom {
        background: #db1e1e70;
        width: 100%;
        padding: 20px 0;
        text-align: center;
        color: #c91c1c;
      }

      .section-team {
        width: 90vw;
        margin: 30px auto;
        min-height: 90vh;
      }

      .section-team .container {
        position: relative;
        display: flex;
        justify-content: center;
        align-items: center;
        flex-wrap: wrap;
      }

      .section-team .container .card {
        position: relative;
        width: 280px;
        height: 400px;
        background: linear-gradient(#c91c1c, #c91c1c 30%, #880808 30%, #880808);
        margin: 10px;
        border-radius: 20px;
        overflow: hidden;
      }

      .section-team .container .card .imgbox {
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        transition: 0.5s;
        /* z-index: 90; */
        transform-origin: top;
        border-radius: 20px;
        overflow: hidden;
        transition: 1s;
      }

      /* .section-team .container .card:hover .imgbox {
                transform: translateY(30px) scale(0.5) rotate(360deg);
              } */

      .section-team .container .card .imgbox img {
        object-fit: contain;
        width: 100%;
        height: 63%;
        transform: scale(0.7);
        aspect-ratio: 3/4;

        /* top: 10px; */
      }

      .section-team .container .card .content {
        position: absolute;
        top: -50px;
        left: 0;
        width: 100%;
        height: 100%;
        display: flex;
        justify-content: center;
        align-items: flex-end;
        padding-bottom: 30px;
        transition: 0.5s;
        /* transform: translateY(100%); */
      }

      /* .section-team .container .card:hover .content {
                transform: translateY(0);
              } */

      .section-team .container .card .content .details {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        text-align: center;
      }

      .section-team .container .card .content .details h2 {
        font-size: 18px;
        color: #fff;
        font-weight: 500;
      }

      .section-team .container .card .content .details h2 span {
        font-size: 15px;
        color: #f2f2f2;
        font-weight: 500;
      }

      .img-folder {
        height: 400px;
        width: 400px;
        position: absolute;
        right: 262px;
        top: 120px;
      }

      .img-folder img {
        width: 100%;
        height: 80%;
      }

      /* contact form style */

      .container_contact {
        align-content: center;
        justify-content: center;
        border-radius: 6px;
      }

      .container_contact .content {
        display: flex;
        align-items: center;
        justify-content: space-between;
        /* max-width: 70%; */
        /* margin: 20px auto; */
        padding: 20px;
      }

      .container_contact .content .contact-side {
        width: 100%;
        height: 100%;
        display: flex;
        flex-direction: row;
        align-items: center;
        justify-content: space-evenly;
        margin-top: 15px;
        position: relative;
      }

      .content .contact-side .details {
        margin: 14px;
        text-align: center;
      }

      .content .contact-side .details i {
        font-size: 30px;
        color: #880808;
        margin-bottom: 10px;
      }

      .content .contact-side .details .topic {
        font-size: 18px;
        font-weight: 500;
        color: #854054;
        padding-bottom: 5px;
      }

      .content .contact-side .details .text-one,
      .content .contact-side .details .text-two {
        font-size: 14px;
        color: #c91c1c;
        width: 70%;
        position: relative;
        margin: auto;
        margin-bottom: 3px;
        white-space: pre-line;
        padding-block: 1% 1%;
        padding-inline: 10px;
        overflow: hidden;
        text-overflow: ellipsis;
        /* line-height: 1.5; */
      }

      @media (max-width: 600px) {
        .container_contact {
          margin: 40px 0;
          height: 100%;
        }

        .container_contact .content {
          flex-direction: column-reverse;
        }

        .container_contact .content .contact-side {
          width: 100%;
          flex-direction: row;
          margin-top: 40px;
          justify-content: center;
          flex-wrap: wrap;
        }

        .container .content .contact-side::before {
          display: none;
        }

        .container .content .right-side {
          width: 100%;
          margin-left: 0;
        }
        .content .contact-side .details {
          margin: 14px;
          text-align: center;
          white-space: nowrap;
          /* width: 100%; */
        }
      }
      .address,
      .phone,
      .email {
        width: 30%;
      }
      .text {
        width: 100%;
        line-break: normal;
      }
    </style>

    <body>
      <!---- Navigation ------>
      <header>
        <div class="tbox" id="tbox">
          <div id="toogle"></div>
        </div>
        <div class="logo">
          <img
            src="https://cdn-icons-png.flaticon.com/512/1297/1297136.png"
            class="fileInput"
            id="image1"
            alt="logo"
            onclick="chooseFile()"
          />
          &nbsp;
        </div>
        <nav class="navigation">
          <div class="nav-list" id="nav-list">
            <ul>
              <li>
                <a
                  class="list editable"
                  href="#home"
                  contenteditable="true"
                  data-max-chars="20"
                  >Home</a
                >
              </li>
              <li>
                <a
                  class="list editable"
                  href="#about"
                  contenteditable="true"
                  data-max-chars="20"
                  >About</a
                >
              </li>
              <li>
                <a
                  class="list editable"
                  href="#services"
                  contenteditable="true"
                  data-max-chars="20"
                  >Services</a
                >
              </li>
              <li>
                <a
                  class="list editable"
                  href="#gallery"
                  contenteditable="true"
                  data-max-chars="20"
                  >Gallery</a
                >
              </li>
              <li>
                <a
                  class="list editable"
                  href="#contact"
                  contenteditable="true"
                  data-max-chars="20"
                  >Contact</a
                >
              </li>
              <li>
                <a
                  class="list editable"
                  href="#team"
                  contenteditable="true"
                  data-max-chars="20"
                  >Team</a
                >
              </li>
            </ul>
          </div>
        </nav>
        <!-- <div class="counter" id="charCount" contenteditable="false">Total chars: 0 (100 remaining)</div> -->
      </header>
      <!---- Navigation End ------>

      <!---- Home Section ------>
      <div id="1">
        <section class="home" id="home">
          <div class="intro">
            <h1 class="editable" contenteditable="true" data-max-chars="100">
              You are someone's Hero - donate blood
            </h1>
            <p class="editable" contenteditable="true" data-max-chars="500">
              In as little as few minutes, you can become someone's unnamed,
              unknown, but all-important Hero. Saving a life is a noble work that
              starts very simply and easily. Donate Blood or donate Money, every
              form of contribution you make is important, valued and essential in
              our shared mission to save lives.
            </p>
            <span type="button" id="button" contenteditable="false">
              <a
                href="#contact"
                class="editable"
                contenteditable="true"
                data-max-chars="20"
              >
                Contact Us
              </a>
            </span>

            <!-- <span contenteditable="False" type="button" id="button" >
            <a href="#" contenteditable="true" class="editable" data-max-chars="20"> Donate Now</a>
          </span> -->
          </div>

          <div class="home-img">
            <img
              class="fileInput"
              id="image2"
              src="https://img.freepik.com/free-vector/human-blood-donate-white-background_1308-111266.jpg?w=4000"
              alt=" Donate-home-page"
              contenteditable="false"
            />
          </div>
        </section>
      </div>
      <!---- Home Section End ------>

      <hr id="about" />

      <!---- About Section ------>
      <div id="2">
        <section class="about">
          <h1 class="heading editable" contenteditable="true" data-max-chars="20">
            About Us
          </h1>
          <div class="about-container">
            <div class="img">
              <img
                class="fileInput"
                id="image3"
                onclick="chooseFile()"
                src="https://www.kokilabenhospital.com/blog/wp-content/uploads/2023/06/World-Blood-Donor-Day-Blog-.jpg"
                alt=""
              />
            </div>
            <div class="text">
              <p contenteditable="true" class="editable" data-max-chars="600">
                Have you at anytime witnessed a relative of yours or a close
                friend searching frantically for a blood donor, when blood banks
                say out of stock, the donors in mind are out of reach and the time
                keep sticking? Have you witnessed loss of life for the only reason
                that a donor was not available at the most needed hour? Is it
                something that we as a society can do nothing to prevent? This
                thought laid our foundation.
              </p>
              <p class="editable" contenteditable="true" data-max-chars="600">
                "We Care" is an organization that brings voluntary blood donors
                and those in need of blood on to a common platform. Through this
                website, we seek donors who are willing to donate blood, as well
                as provide the timeliest support to those in frantic need of it.
              </p>
            </div>
          </div>
        </section>
      </div>
      <!---- About Section End ------>

      <hr id="services" />

      <!---- Services Section ------>
      <div id="3">
        <section class="services" id="services">
          <h2 class="heading editable" contenteditable="true" data-max-chars="30">
            Our Services
          </h2>
          <div class="service-container">
            <div class="box">
              <i class="fas fa-bone"></i>

              <h3 contenteditable="true" class="editable" data-max-chars="30">
                What we do
              </h3>

              <p class="editable" contenteditable="true" data-max-chars="250">
                We Care connect blood donors with recipients, without any
                intermediary such as blood banks, for an efficient and seamless
                process.
              </p>
            </div>
            <div class="box">
              <i class="fas fa-user-graduate"></i>
              <h3 contenteditable="true" class="editable" data-max-chars="30">
                Innovative
              </h3>
              <p class="editable" contenteditable="true" data-max-chars="250">
                We Care is an innovative approach to address global health. We
                provide immediate access to blood donors.
              </p>
            </div>
            <div class="box">
              <i class="fas fa-child"></i>
              <h3 contenteditable="true" class="editable" data-max-chars="30">
                Network
              </h3>
              <p class="editable" contenteditable="true" data-max-chars="250">
                We Care is one of several community organizations working together
                as a network that responds to emergencies in an efficient manner.
              </p>
            </div>
            <div class="box">
              <i class="fas fa-hand-holding-usd"></i>
              <h3 contenteditable="true" class="editable" data-max-chars="30">
                Get notified
              </h3>
              <p class="editable" contenteditable="true" data-max-chars="250">
                We Care works with network partners to connect blood donors and
                recipients through an automated SMS service and a mobile app.
              </p>
            </div>
            <div class="box">
              <i class="fas fa-hands-helping"></i>
              <h3 contenteditable="true" class="editable" data-max-chars="30">
                Totally Free
              </h3>
              <p class="editable" contenteditable="true" data-max-chars="250">
                We Care's ultimate goal is to provide an easy-to-use,
                easy-to-access, fast, efficient, and reliable way to get
                life-saving blood, totally Free of cost.
              </p>
            </div>
            <div class="box">
              <i class="fas fa-people-carry"></i>
              <h3 contenteditable="true" class="editable" data-max-chars="30">
                Save Life
              </h3>
              <p class="editable" contenteditable="true" data-max-chars="250">
                We are a non profit foundation and our main objective is to make
                sure that everything is done to protect vulnerable persons. Help
                us by making a gift !
              </p>
            </div>
            <div class="box">
              <i class="fas fa-hand-holding-water"></i>
              <h3 contenteditable="true" class="editable" data-max-chars="30">
                Alaways Available
              </h3>
              <p class="editable" contenteditable="true" data-max-chars="250">
                We Care gives you 24x7 Service that too Free Of Cost
              </p>
            </div>
            <div class="box">
              <i class="fas fa-hand-holding-medical"></i>
              <h3 contenteditable="true" class="editable" data-max-chars="30">
                MEDICAL FACILITIES
              </h3>
              <p class="editable" contenteditable="true" data-max-chars="250">
                We provide free of cost medical services to needy people and
                provide treatment faciliries and rehabilation centers are also
                there
              </p>
            </div>
          </div>
        </section>
      </div>
      <!---- Services Section End ------>

      <hr id="gallery" />

      <!---- Gallery Section ------>
      <div id="4">
        <section class="gallery-img" id="gallery">
          <h1 class="heading editable" contenteditable="true" data-max-chars="30">
            Our Gallary
          </h1>
          <div class="gbox">
            <div class="gallery">
              <div class="imgContainer">
                <div class="subContainer">
                  <img
                    onclick="chooseFile()"
                    class="fileInput"
                    id="image4"
                    src=""
                    alt="image name"
                  />
                </div>
              </div>
              <div
                class="desc editable"
                contenteditable="true"
                data-max-chars="100"
              >
                Donation camp 1
              </div>
            </div>

            <div class="gallery">
              <div class="imgContainer">
                <div class="subContainer">
                  <img
                    onclick="chooseFile()"
                    class="fileInput"
                    id="image5"
                    src=""
                    alt="image name"
                  />
                </div>
              </div>
              <div
                class="desc editable"
                contenteditable="true"
                data-max-chars="100"
              >
                Donation camp 2
              </div>
            </div>
            <div class="gallery">
              <div class="imgContainer">
                <div class="subContainer">
                  <img
                    onclick="chooseFile()"
                    class="fileInput"
                    id="image6"
                    src=""
                    alt="image name"
                  />
                </div>
              </div>
              <div
                class="desc editable"
                contenteditable="true"
                data-max-chars="100"
              >
                Donation camp 3
              </div>
            </div>

            <div class="gallery">
              <div class="imgContainer">
                <div class="subContainer">
                  <img
                    onclick="chooseFile()"
                    class="fileInput"
                    id="image7"
                    src=""
                    alt="image name"
                  />
                </div>
              </div>
              <div
                class="desc editable"
                contenteditable="true"
                data-max-chars="100"
              >
                Donation camp 4
              </div>
            </div>
            <div class="gallery">
              <div class="imgContainer">
                <div class="subContainer">
                  <img
                    onclick="chooseFile()"
                    class="fileInput"
                    id="image8"
                    src=""
                    alt="image name"
                  />
                </div>
              </div>
              <div
                class="desc editable"
                contenteditable="true"
                data-max-chars="100"
              >
                Donation camp 5
              </div>
            </div>
          </div>
        </section>
      </div>
      <!---- Gallery Section End ------>

      <hr id="contact" />

      <!---- Contact Us Section ------>
      <div id="5">
        <section class="contact" id="contact">
          <h1 class="heading editable" contenteditable="true" data-max-chars="30">
            Contact Us
          </h1>
          <div class="container_contact">
            <div class="content">
              <div class="contact-side">
                <div class="address details">
                  <i class="fas fa-map-marker-alt"></i>
                  <div class="topic">Address</div>
                  <div class="text">
                    <div
                      class="text-one editable"
                      contenteditable="true"
                      data-max-chars="100"
                    >
                      ahmedabad, 380052
                    </div>
                    <div
                      class="text-two editable"
                      contenteditable="true"
                      data-max-chars="100"
                    >
                      ahmedabad 360002
                    </div>
                  </div>
                </div>
                <div class="phone details">
                  <i class="fas fa-phone-alt"></i>
                  <div class="topic">Phone</div>
                  <div class="text">
                    <div
                      class="text-one editable"
                      contenteditable="true"
                      data-max-chars="20"
                    >
                      +0098 9893 5647
                    </div>
                    <div
                      class="text-two editable"
                      contenteditable="true"
                      data-max-chars="20"
                    >
                      +0096 3434 5678
                    </div>
                  </div>
                </div>
                <div class="email details">
                  <i class="fas fa-envelope"></i>
                  <div class="topic">Email</div>
                  <div class="text">
                    <div
                      class="text-one editable"
                      contenteditable="true"
                      data-max-chars="60"
                    >
                      blooddonateahmedabad@gmail.com
                    </div>
                    <div
                      class="text-two editable"
                      contenteditable="true"
                      data-max-chars="60"
                    >
                      blooddonationssurat@gmail.com
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </section>
      </div>
      <!---- Contact Us Section End ------>

      <hr id="team" />

      <!---- Teams Section ------>
      <div id="6">
        <section class="section-team" id="team">
          <h1 class="heading editable" contenteditable="true" data-max-chars="30">
            Our Team
          </h1>
          <div class="container">
            <div class="card">
              <div class="imgbox">
                <img
                    onclick="chooseFile()"
                    class="fileInput"
                    id="image13"
                    src=""
                    alt="image name"
                />
              </div>
              <div class="content">
                <div class="details" contenteditable="false">
                  <h2 contenteditable="true" class="editable" data-max-chars="20">
                    Saurabh Pandey<br /><span contenteditable="true">xyz</span>
                  </h2>
                </div>
              </div>
            </div>
            <div class="card">
              <div class="imgbox">
                <img
                  onclick="chooseFile()"
                  class="fileInput"
                  id="image14"
                  src="https://img.freepik.com/free-photo/portrait-white-man-isolated_53876-40306.jpg"
                  alt="image"
                />
              </div>
              <div class="content">
                <div class="details" contenteditable="false">
                  <h2 contenteditable="true" class="editable" data-max-chars="50">
                    Shruti Shreya<br /><span contenteditable="true">xyz</span>
                  </h2>
                </div>
              </div>
            </div>
            <div class="card">
              <div class="imgbox">
                <img
                  class="fileInput"
                  id="image15"
                  onclick="chooseFile()"
                  src="https://www.bareinternational.in/wp-content/uploads/sites/3/2018/02/AdobeStock_115483122.jpeg"
                  alt="image"
                />
              </div>
              <div class="content">
                <div class="details" contenteditable="false">
                  <h2 contenteditable="true" class="editable" data-max-chars="50">
                    Alok Kumar Jha<br /><span contenteditable="true">xyz</span>
                  </h2>
                </div>
              </div>
            </div>
            <div class="card">
              <div class="imgbox">
                <img
                  class="fileInput"
                  id="image16"
                  onclick="chooseFile()"
                  src="https://fixthephoto.com/blog/images/uikit_slider/male-photo-edited-by-fixthephoto_1649799183.jpg"
                  alt="image"
                />
              </div>
              <div class="content">
                <div class="details" contenteditable="false">
                  <h2 contenteditable="true" class="editable" data-max-chars="50">
                    Bhuvanyu Oliyan<br /><span contenteditable="true">xyz</span>
                  </h2>
                </div>
              </div>
            </div>
          </div>
        </section>
      </div>
      <!---- Teams Section End ------>

      <!------------- Footer ------------->
      <footer>
        <div class="footer-content">
          <h1 class="heading editable" contenteditable="true" data-max-chars="20">
            We Care
          </h1>
          <p contenteditable="true" class="editable" data-max-chars="150">
            We are ready to help you anytime because
            <b contenteditable="true">we care..</b>
          </p>
        </div>
        <div class="footer-bottom" contenteditable="false">
          <p contenteditable="true" class="editable" data-max-chars="100">
            Copyright &copy; 2021 We Care. Designed by
            <span contenteditable="false">WDA</span>
          </p>
        </div>
      </footer>
      <!------------- Footer End ------------->
      <script>
          const h2Element = document.getElementById("vaibhav");
          const maxChars = parseInt(h2Element.getAttribute("data-max-chars"));
        
          // Function to truncate text and prevent further input
          function limitText() {
            let text = h2Element.innerText.replace(/\s+/g, ''); // Remove whitespace characters
        
            if (text.length > maxChars) {
              // Truncate the text to the maximum allowed characters
              h2Element.innerText = text.slice(0, maxChars);
            }
          }
        
          // Initialize the content with the maximum character limit
          limitText();
        
          // Add an input event listener to monitor changes
          h2Element.addEventListener("input", limitText);
        
          // Disable text selection and context menu on mobile devices
          h2Element.addEventListener("selectstart", function (e) {
            e.preventDefault();
          });
          h2Element.addEventListener("contextmenu", function (e) {
            e.preventDefault();
          });
        </script>
      <script id="imgMapping">
        function onImageTap(imgId) {
          AndroidInterface.onImageTap(imgId);
        }

        function setImages(data) {
          const imgdata = JSON.parse(data);
          const img = document.getElementById(imgdata.id);

          if (img) {
            img.src = "http://${ipaddress}/" + imgdata.url;
          }
        }
      </script>
      <script id="navToggle">
        document.getElementById("tbox").addEventListener("click", function () {
          document.getElementById("nav-list").classList.toggle("active");

          document.getElementById("toogle").classList.toggle("active");
        });
      </script>
    </body>
  </html>

    
""".trimIndent()}