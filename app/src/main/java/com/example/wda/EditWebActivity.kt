package com.example.wda

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
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
        }
        binding.textFont2.setOnClickListener {
            binding.textFont1.isChecked = false
            binding.textFont2.isChecked = true
            binding.textFont3.isChecked = false
            binding.textFont4.isChecked = false
            binding.textFont5.isChecked = false
        }
        binding.textFont3.setOnClickListener {
            binding.textFont1.isChecked = false
            binding.textFont2.isChecked = false
            binding.textFont3.isChecked = true
            binding.textFont4.isChecked = false
            binding.textFont5.isChecked = false
        }
        binding.textFont4.setOnClickListener {
            binding.textFont1.isChecked = false
            binding.textFont2.isChecked = false
            binding.textFont3.isChecked = false
            binding.textFont4.isChecked = true
            binding.textFont5.isChecked = false
        }
        binding.textFont5.setOnClickListener {
            binding.textFont1.isChecked = false
            binding.textFont2.isChecked = false
            binding.textFont3.isChecked = false
            binding.textFont4.isChecked = false
            binding.textFont5.isChecked = true
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

        }
        binding.colorPalet2.setOnClickListener {
            binding.colorPalet1.isChecked = false
            binding.colorPalet2.isChecked = true
            binding.colorPalet3.isChecked = false
            binding.colorPalet4.isChecked = false
            binding.colorPalet5.isChecked = false
        }
        binding.colorPalet3.setOnClickListener {
            binding.colorPalet1.isChecked = false
            binding.colorPalet2.isChecked = false
            binding.colorPalet3.isChecked = true
            binding.colorPalet4.isChecked = false
            binding.colorPalet5.isChecked = false
        }
        binding.colorPalet4.setOnClickListener {
            binding.colorPalet1.isChecked = false
            binding.colorPalet2.isChecked = false
            binding.colorPalet3.isChecked = false
            binding.colorPalet4.isChecked = true
            binding.colorPalet5.isChecked = false
        }
        binding.colorPalet5.setOnClickListener {
            binding.colorPalet1.isChecked = false
            binding.colorPalet2.isChecked = false
            binding.colorPalet3.isChecked = false
            binding.colorPalet4.isChecked = false
            binding.colorPalet5.isChecked = true
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

        btn = findViewById(R.id.imagePathBtn)

        web = findViewById(R.id.webView)
        printLayout=findViewById(R.id.layoutid)
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
        web.loadDataWithBaseURL(null, getHtmlContent(), "text/html", "UTF-8", null)
//        web.loadUrl("http://${ipaddress}/wda/VaibhavEnterprise.html")
        web.webChromeClient = WebChromeClient()

        web.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                btn.isEnabled = true
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
//                        withContext(Dispatchers.IO){
//                            user.websiteRegister("6502c872293c6cecbee8e290","Vaibhav Enterprise",base64Html,"Organisational","16-09-2023","6484984894","4684646354","6846164836")
//                        }
                    }
                }
            }

        }

        binding =ActivityEditWebBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
        ActivityCompat.startActivityForResult(this@EditWebActivity, intent,FILE_PICKER_REQUEST_CODE,
            Bundle.EMPTY
        )
    }

    @OptIn(DelicateCoroutinesApi::class)
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode!= AppCompatActivity.RESULT_CANCELED){
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



private fun getHtmlContent(): String {
    return """
            <!DOCTYPE html>
            <html lang="en">
              <head>
                <meta charset="UTF-8" />
                <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                <meta name="viewport" content="width=device-width, initial-scale=1.0" />
                <title>We Care</title>

                 <script>
                      function chooseFile() {
                           AndroidInterface.showFileChooser(event.target.id);
                      }
                      function yourFileUploadHandler(filePaths) {
                            console.log(filePaths);
                      }
                </script>

                <link
                  rel="stylesheet"
                  href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
                />

                <!------- Favicon Linkage --------->
                <link rel="shortcut icon" href="fav.png" type="x-icon" />
              </head>

              <style contenteditable="True">
                @import url("https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700;900&display=swap");

                * {
                  margin: 0;
                  padding: 0;
                  box-sizing: border-box;
                  text-decoration: none;
                  list-style: none;
                  scroll-behavior: smooth;
                }

                :root {
                  --body: #fff;
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
                img { pointer-events: auto; }
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

                nav.navigation {
                  display: flex;
                  justify-content: space-between;
                  align-items: center;
                  width: 80vw;
                  margin: 0 auto;
                  padding: 10px 0;
                }

                nav.navigation .logo img {
                  height: 30px;
                  width: auto;
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

                /* nav.navigation .nav-list ul li a:hover {
              color: #db1e1e;
            } */

                #toogle {
                  display: none;
                }

                @media (max-width: 923px) {
                  header {
                    min-height: 70px;
                  }

                  nav.navigation .logo {
                    display: none;
                  }

                  nav.navigation .nav-list {
                    margin: 10px auto;
                  }

                  nav.navigation .nav-list ul {
                    display: none;
                    justify-content: center;
                    align-items: center;
                    flex-direction: column;
                    gap: 10px;
                  }

                  .tbox {
                    height: 50px;
                    width: 50px;
                    cursor: pointer;
                    position: absolute;
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

                  nav.navigation .nav-list.active ul {
                    display: flex;
                    padding: 10px;
                  }

                  nav.navigation .nav-list.active ul li {
                    margin: 20px 0;
                    /* height: 100vh; */
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

                /* @keyframes anime {
              0% {
                transform: translateY(0px);
              }

              25% {
                transform: translateY(-8px);
              }

              50% {
                transform: translateY(0px);
              }

              75% {
                transform: translateY(10px);
              }

              100% {
                transform: translateY(0px);
              }
            } */

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
                  font-size: 15px;
                  color: #373738;
                  line-height: 30px;
                  font-weight: 400;
                }

                @media (max-width: 650px) {
                  .about-container {
                    flex-direction: column;
                    margin-top: 80px;
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
                }

                .services .service-container .box p {
                  font-size: 15px;
                  line-height: 24px;
                  font-weight: 400;
                  color: #373738;
                }

                @media (max-width: 910px) {
                  .services .service-container {
                    grid-template-columns: repeat(3, 1fr);
                  }
                }

                @media (max-width: 700px) {
                  .services .service-container {
                    grid-template-columns: repeat(2, 1fr);
                  }
                }

                @media (max-width: 400px) {
                  .services .service-container {
                    grid-template-columns: repeat(1, 1fr);
                  }
                }

                .gallary-img {
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
                /* height: 80%; */
                /* margin: 5px; */
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
                }

                @media (max-width: 412px) {
                  div.gallery,
                  div.gallery img {
                    width: 100%;
                  }
                }

                .contact {
                  width: 90vw;
                  margin: 0 auto;
                }

                .contact h1,
                .heading {
                  font-size: 35px;
                  color: #c91c1c;
                  line-height: 50px;
                  font-weight: 700;
                  text-transform: uppercase;
                  text-align: center;
                  margin-bottom: 30px;
                }

                .contact .input-wrap {
                  display: grid;
                  grid-template-columns: repeat(2, 1fr);
                  grid-gap: 2rem;
                  margin-bottom: 2rem;
                }

                .contact .input-wrap-2 {
                  display: flex;
                  flex-direction: column;
                }

                .contact input {
                  padding: 0.6rem;
                  border: 1px solid #ddd;
                  font-size: 0.9rem;
                  background-color: #e6e6e6;
                }

                .contact .input-wrap-2 input {
                  margin-bottom: 2rem;
                  border: 1px solid #ddd;
                  font-size: 0.9rem;
                  background-color: #e6e6e6;
                }

                .contact .input-wrap-2 textarea {
                  padding: 0.6rem;
                  border: 1px solid #ddd;
                  margin-bottom: 2rem;
                  font-size: 0.9rem;
                  background-color: #e6e6e6;
                }

                .contact input:focus {
                  outline: none;
                }

                .contact textarea:focus {
                  outline: none;
                }

                .contact .btn-wrapper {
                  text-align: center;
                }

                .contact .btn {
                  width: 90vw;
                  display: flex;
                  justify-content: center;
                  align-items: center;
                  margin-bottom: 30px;
                }

                .contact .btn button {
                  outline: none;
                  color: var(--body);
                  background-color: #c91c1c;
                  cursor: pointer;
                  font-size: 1rem;
                  padding: 1.075rem 1.25rem;
                  transition: ease-in-out 0.7s;
                  border: 1px solid transparent;
                }

                /* .contact .btn button:hover {
              background-color: var(--body);
              color: #c91c1c;
              border-color: #c91c1c;
            } */

                @media (max-width: 462px) {
                  .contact .input-wrap {
                    grid-template-columns: repeat(1, 1fr);
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

                footer .footer-content .solink {
                  list-style: none;
                  display: flex;
                  align-items: center;
                  justify-content: center;
                  margin: 10px 0 30px 0;
                }

                footer .footer-content .solink li {
                  margin: 0 10px;
                }

                footer .footer-content .solink a {
                  text-decoration: none;
                  color: #db1e1e;
                  transition: all 0.5s ease;
                }
                footer .footer-content .solink a i {
                  font-size: 20px;
                }

                footer .footer-content .solink a:hover i {
                  color: #fd1b5b;
                }

                footer .footer-bottom {
                  background: #db1e1e70;
                  width: 100%;
                  padding: 20px 0;
                  text-align: center;
                  color: #c91c1c;
                }

                ::-webkit-scrollbar-track {
                  box-shadow: inset 0 0 6px #0000004d;
                  background-color: var(--body);
                }

                ::-webkit-scrollbar {
                  width: 15px;
                  background-color: var(--body);
                }

                ::-webkit-scrollbar-thumb {
                  background-color: #c91c1c;
                  border-radius: 10px;
                }

                .section-team {
                  width: 90vw;
                  margin: 30px auto;
                  min-height: 100vh;
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
                  /* top: 10px; */
                }

                .section-team .container .card .content {
                  position: absolute;
                  top: 0;
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

                .section-team .container .card .content .details .social {
                  display: flex;
                  margin-top: 7px;
                }

                .section-team .container .card .content .details .social li a {
                  height: 45px;
                  width: 45px;
                  display: flex;
                  justify-content: center;
                  align-items: center;
                  margin: 5px;
                  background-color: #db1e1e;
                  border-radius: 50%;
                  color: #fff;
                  font-size: 20px;
                  /* transition: 0.5s; */
                }

                /* .section-team .container .card .content .details .social li a:hover {
              transform: rotate(360deg);
            } */

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
                  animation: animeX 6s infinite linear;
                }

                @keyframes animeX {
                  0% {
                    transform: translateX(0px);
                  }

                  25% {
                    transform: translateX(-30px);
                  }

                  50% {
                    transform: translateX(0px);
                  }

                  75% {
                    transform: translateX(30px);
                  }

                  100% {
                    transform: translateX(0px);
                  }
                }
              </style>
              <body>
                <!---- Navigation ------>
                <header>
                  <div class="tbox" id="tbox">
                    <div id="toogle"></div>
                  </div>
                  <nav class="navigation">
                    <div class="logo">
                      <img src="logo2.png" class="fileInput" id="image1" alt="logo" onclick=chooseFile() />
                    </div>
                    <div class="nav-list" id="nav-list">
                      <ul>
                        <li><a class="list" href="#home">Home</a></li>
                        <li><a class="list" href="#about">About</a></li>
                        <li><a class="list" href="#services">Services</a></li>
                        <li><a class="list" href="#gallery">Gallery</a></li>
                        <li><a class="list" href="#contact">Contact</a></li>
                        <li><a class="list" href="#team">Team</a></li>
                      </ul>
                    </div>
                  </nav>
                </header>
                <!---- Navigation End ------>

                <!---- Home Section ------>
                <div id="1" >
                  <section class="home" id="home" >
                    <div class="intro" contenteditable="True">
                      <h1>You are someone's Hero - donate blood</h1>
                      <p>
                        In as little as few minutes, you can become someone's unnamed,
                        unknown, but all-important Hero. Saving a life is a noble work that
                        starts very simply and easily. Donate Blood or donate Money, every
                        form of contribution you make is important, valued and essential in
                        our shared mission to save lives.
                      </p>
                      <span contenteditable="False"><a href="#contact">Contact Us</a></span>
                      <span contenteditable="False"><a href="#" contenteditable="True">Donate Now</a></span>
                    </div>
                    <div class="home-img" contenteditable="false">
                      <img
                        class="fileInput" id="image2"
                        src=""
                        alt=" Donate-home-page" contenteditable="false"
                      />
                    </div>
                  </section>
                </div>
                <!---- Home Section End ------>

                <hr id="about" />

                <!---- About Section ------>
                <div id="2">
                  <section class="about">
                    <h1 class="heading">About Us</h1>
                    <div class="about-container">
                      <div class="img">
                        <img
                          class="fileInput" id="image3"
                          onclick=chooseFile()
                          src=""
                          alt=""
                        />
                      </div>
                      <div class="text">
                        <p>
                          Have you at anytime witnessed a relative of yours or a close
                          friend searching frantically for a blood donor, when blood banks
                          say out of stock, the donors in mind are out of reach and the time
                          keep sticking? Have you witnessed loss of life for the only reason
                          that a donor was not available at the most needed hour? Is it
                          something that we as a society can do nothing to prevent? This
                          thought laid our foundation.
                        </p>
                        <p>
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
                  <section class="services">
                    <h2 class="heading">Our Services</h2>
                    <div class="service-container">
                      <div class="box">
                        <i class="fas fa-bone"></i>
                        <h3>What we do</h3>
                        <p>
                          We Care connect blood donors with recipients, without any
                          intermediary such as blood banks, for an efficient and seamless
                          process.
                        </p>
                      </div>
                      <div class="box">
                        <i class="fas fa-user-graduate"></i>
                        <h3>Innovative</h3>
                        <p>
                          We Care is an innovative approach to address global health. We
                          provide immediate access to blood donors.
                        </p>
                      </div>
                      <div class="box">
                        <i class="fas fa-child"></i>
                        <h3>Network</h3>
                        <p>
                          We Care is one of several community organizations working together
                          as a network that responds to emergencies in an efficient manner.
                        </p>
                      </div>
                      <div class="box">
                        <i class="fas fa-hand-holding-usd"></i>
                        <h3>Get notified</h3>
                        <p>
                          We Care works with network partners to connect blood donors and
                          recipients through an automated SMS service and a mobile app.
                        </p>
                      </div>
                      <div class="box">
                        <i class="fas fa-hands-helping"></i>
                        <h3>Totally Free</h3>
                        <p>
                          We Care's ultimate goal is to provide an easy-to-use,
                          easy-to-access, fast, efficient, and reliable way to get
                          life-saving blood, totally Free of cost.
                        </p>
                      </div>
                      <div class="box">
                        <i class="fas fa-people-carry"></i>
                        <h3>Save Life</h3>
                        <p>
                          We are a non profit foundation and our main objective is to make
                          sure that everything is done to protect vulnerable persons. Help
                          us by making a gift !
                        </p>
                      </div>
                      <div class="box">
                        <i class="fas fa-hand-holding-water"></i>
                        <h3>Alaways Available</h3>
                        <p>We Care gives you 24x7 Service that too Free Of Cost</p>
                      </div>
                      <div class="box">
                        <i class="fas fa-hand-holding-medical"></i>
                        <h3>MEDICAL FACILITIES</h3>
                        <p>
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
                  <section class="gallary-img">
                    <h1 class="heading">Our Gallary</h1>
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
                        <div class="desc">Donation camp 1</div>
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
                        <div class="desc">Donation camp 2</div>
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
                        <div class="desc">Donation camp 3</div>
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
                        <div class="desc">Blood Donation Campaign</div>
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
                        <div class="desc">Our team in Nigeria helping children</div>
                      </div>
                      
                    </div>
                  </section>
                </div>
                <!---- Gallery Section End ------>

                <hr id="contact" />

                <!---- Contact Us Section ------>
                <div id="5">
                  <section class="contact">
                    <h1>Contact Us</h1>
                    <form action="#" method="POST">
                      <div class="input-wrap">
                        <input type="text" placeholder="Your Name" name="name" required />
                        <input
                          type="email"
                          placeholder="Your Email"
                          name="email"
                          required
                        />
                      </div>
                      <div class="input-wrap-2">
                        <input
                          type="text"
                          placeholder="Your Subject...."
                          name="subject"
                          required
                        />
                        <textarea
                          name="msg"
                          id=""
                          cols="30"
                          rows="10"
                          placeholder="Your Message"
                          required
                        ></textarea>
                      </div>
                      <div class="btn">
                        <button type="submit">Send Message</button>
                      </div>
                    </form>
                  </section>
                </div>
                <!---- Contact Us Section End ------>

                <hr id="team" />

                <!---- Teams Section ------>
                <div id="6">
                  <section class="section-team">
                    <h1 class="heading">Our Team</h1>
                    <div class="container">
                      <div class="card">
                        <div class="imgbox">
                          <img
                            class="fileInput" id="image13"
                            onclick=chooseFile()
                            src=""
                            alt="image"
                          />
                        </div>
                        <div class="content">
                          <div class="details">
                            <h2>Saurabh Pandey<br /><span>xyz</span></h2>
                            <ul class="social">
                              <li>
                                <a href="#" target="blank"
                                  ><i class="fab fa-twitter"></i
                                ></a>
                              </li>
                              <li>
                                <a href="#" target="blank"><i class="fab fa-github"></i></a>
                              </li>
                              <li>
                                <a href="#" target="blank"
                                  ><i class="fab fa-instagram"></i
                                ></a>
                              </li>
                              <li>
                                <a href="#" target="blank"
                                  ><i class="fab fa-linkedin-in"></i
                                ></a>
                              </li>
                            </ul>
                          </div>
                        </div>
                      </div>
                      <div class="card">
                        <div class="imgbox">
                          <img onclick=chooseFile() class="fileInput" id="image14" src="./assets/images/team_members/shruti.jpg" alt="image" />
                        </div>
                        <div class="content">
                          <div class="details">
                            <h2>Shruti Shreya<br /><span>xyz</span></h2>
                            <ul class="social">
                              <li>
                                <a href="#" target="blank"
                                  ><i class="fab fa-twitter"></i
                                ></a>
                              </li>
                              <li>
                                <a href="#" target="blank"><i class="fab fa-github"></i></a>
                              </li>
                              <li>
                                <a href="#" target="blank"
                                  ><i class="fab fa-instagram"></i
                                ></a>
                              </li>
                              <li>
                                <a href="#" target="blank"
                                  ><i class="fab fa-linkedin-in"></i
                                ></a>
                              </li>
                            </ul>
                          </div>
                        </div>
                      </div>
                      <div class="card">
                        <div class="imgbox">
                          <img
                            class="fileInput" id="image15"
                            onclick=chooseFile()
                            src=""
                            alt="image"
                          />
                        </div>
                        <div class="content">
                          <div class="details">
                            <h2>Alok Kumar Jha<br /><span>xyz</span></h2>
                            <ul class="social">
                              <li>
                                <a href="#" target="blank"
                                  ><i class="fab fa-twitter"></i
                                ></a>
                              </li>
                              <li>
                                <a href="#" target="blank"><i class="fab fa-github"></i></a>
                              </li>
                              <li>
                                <a href="#" target="blank"
                                  ><i class="fab fa-instagram"></i
                                ></a>
                              </li>
                              <li>
                                <a href="#" target="blank"
                                  ><i class="fab fa-linkedin-in"></i
                                ></a>
                              </li>
                            </ul>
                          </div>
                        </div>
                      </div>
                      <div class="card">
                        <div class="imgbox">
                          <img
                            class="fileInput" id="image16"
                            onclick=chooseFile()
                            src=""
                            alt="image"
                          />
                        </div>
                        <div class="content">
                          <div class="details">
                            <h2>Bhuvanyu Oliyan<br /><span>xyz</span></h2>
                            <ul class="social">
                              <li>
                                <a href="#" target="blank"
                                  ><i class="fab fa-twitter"></i
                                ></a>
                              </li>
                              <li>
                                <a href="#" target="blank"><i class="fab fa-github"></i></a>
                              </li>
                              <li>
                                <a href="#" target="blank"
                                  ><i class="fab fa-instagram"></i
                                ></a>
                              </li>
                              <li>
                                <a href="#" target="blank"
                                  ><i class="fab fa-linkedin-in"></i
                                ></a>
                              </li>
                            </ul>
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
                    <h1 class="heading">We Care</h1>
                    <p>We are ready to help you anytime because <b>we care..</b></p>
                    <ul class="solink">
                      <li>
                        <a href="#" target="blank"><i class="fab fa-twitter"></i></a>
                      </li>
                      <li>
                        <a href="#" target="blank"><i class="fab fa-github"></i></a>
                      </li>
                      <li>
                        <a href="#" target="blank"><i class="fab fa-instagram"></i></a>
                      </li>
                      <li>
                        <a href="#" target="blank"><i class="fab fa-linkedin-in"></i></a>
                      </li>
                    </ul>
                  </div>
                  <div class="footer-bottom">
                    <p>Copyright &copy; 2021 We Care. Designed by <span>Neel Shah</span></p>
                  </div>
                </footer>
                <!------------- Footer End ------------->
                <script>
                    function onImageTap(imgId) {
                        AndroidInterface.onImageTap(imgId);
                    }

                    function setImages(data) {
                         const imgdata = JSON.parse(data);
                         const img = document.getElementById(imgdata.id);

                         if(img){
                            img.src="http://${ipaddress}/"+imgdata.url;
                         }
                    }
                </script>
              </body>
            </html>
        """.trimIndent()
}