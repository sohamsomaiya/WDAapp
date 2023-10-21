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
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.wda.ModalBottomSheet.Companion.contentEditableEnabled
import com.example.wda.ModalBottomSheet.Companion.fontFamily
import com.example.wda.ModalBottomSheet.Companion.primaryColor
import com.example.wda.ModalBottomSheet.Companion.primaryVariantColor
import com.example.wda.ModalBottomSheet.Companion.secondaryColor
import com.example.wda.ModalBottomSheet.Companion.secondaryVariantColor
import com.example.wda.ModalBottomSheet.Companion.textColor
import com.example.wda.ModalBottomSheet.Companion.web
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
        var fontFamily :String? = null
         var primaryColor :String? = null
         var primaryVariantColor :String? = null
         var secondaryColor :String? = null
         var secondaryVariantColor :String? = null
         var contentEditableEnabled = true
         var textColor :String? = null
            lateinit var web: WebView

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
//        primaryColor = ""
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
            fontFamily = "Arial, sans-serif"
            val jsCode = "javascript:document.documentElement.style.setProperty('--font-family', '$fontFamily');"
            web.loadUrl(jsCode)
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
            fontFamily = "Consolas , monospace"
            val jsCode = "javascript:document.documentElement.style.setProperty('--font-family', '$fontFamily');"
            web.loadUrl(jsCode)
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
            fontFamily = "Gill Sans"
            val jsCode = "javascript:document.documentElement.style.setProperty('--font-family', '$fontFamily');"
            web.loadUrl(jsCode)
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
            fontFamily = "Verdana, Geneva, sans-serif"
            val jsCode = "javascript:document.documentElement.style.setProperty('--font-family', '$fontFamily');"
            web.loadUrl(jsCode)
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
            fontFamily = "Calisto MT,serif"
            val jsCode = "javascript:document.documentElement.style.setProperty('--font-family', '$fontFamily');"
            web.loadUrl(jsCode)
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
            primaryColor = "#F9CCD3"
            primaryVariantColor = "#AA1945"
            secondaryColor  = "#F1CED4"
            secondaryVariantColor = "#391306"
            textColor="#000000"
            val jsCode = "javascript:document.documentElement.style.setProperty('--primary-color', '$primaryColor');" +
                    "javascript:document.documentElement.style.setProperty('--primary-variant-color', '$primaryVariantColor');" +
                    "javascript:document.documentElement.style.setProperty('--secondary-color', '$secondaryColor');" +
                    "javascript:document.documentElement.style.setProperty('--secondary-variant-color', '$secondaryVariantColor');" +
                    "javascript:document.documentElement.style.setProperty('--text-color', '$textColor');"
            web.loadUrl(jsCode)
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
            primaryColor = "#AC8E69"
            primaryVariantColor = "#5C8491"
            secondaryColor  = "#C0B3A0"
            secondaryVariantColor = "#35454C"
            textColor="#000000"

            val jsCode = "javascript:document.documentElement.style.setProperty('--primary-color', '$primaryColor');" +
                    "javascript:document.documentElement.style.setProperty('--primary-variant-color', '$primaryVariantColor');" +
                    "javascript:document.documentElement.style.setProperty('--secondary-color', '$secondaryColor');" +
                    "javascript:document.documentElement.style.setProperty('--secondary-variant-color', '$secondaryVariantColor');" +
                    "javascript:document.documentElement.style.setProperty('--text-color', '$textColor');"
            web.loadUrl(jsCode)
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
            primaryColor = "#591F20"
            primaryVariantColor = "#D5245F"
            secondaryColor  = "#989FC0"
            secondaryVariantColor = "#EEE5E0"
            textColor="#000000"
            val jsCode = "javascript:document.documentElement.style.setProperty('--primary-color', '$primaryColor');" +
                    "javascript:document.documentElement.style.setProperty('--primary-variant-color', '$primaryVariantColor');" +
                    "javascript:document.documentElement.style.setProperty('--secondary-color', '$secondaryColor');" +
                    "javascript:document.documentElement.style.setProperty('--secondary-variant-color', '$secondaryVariantColor');" +
                    "javascript:document.documentElement.style.setProperty('--text-color', '$textColor');"
            web.loadUrl(jsCode)
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
            primaryColor = "#004C50"
            primaryVariantColor = "#00939C"
            secondaryColor  = "#10ABB4"
            secondaryVariantColor = "#9C3E00"
            textColor="#000000"

            val jsCode = "javascript:document.documentElement.style.setProperty('--primary-color', '$primaryColor');" +
                    "javascript:document.documentElement.style.setProperty('--primary-variant-color', '$primaryVariantColor');" +
                    "javascript:document.documentElement.style.setProperty('--secondary-color', '$secondaryColor');" +
                    "javascript:document.documentElement.style.setProperty('--secondary-variant-color', '$secondaryVariantColor');" +
                    "javascript:document.documentElement.style.setProperty('--text-color', '$textColor');"
            web.loadUrl(jsCode)
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
            primaryColor = "#024959"
            primaryVariantColor = "#BF5B04"
            secondaryColor  = "#D9B29C"
            secondaryVariantColor = "#A66249"
            textColor="#000000"
            val jsCode = "javascript:document.documentElement.style.setProperty('--primary-color', '$primaryColor');" +
                    "javascript:document.documentElement.style.setProperty('--primary-variant-color', '$primaryVariantColor');" +
                    "javascript:document.documentElement.style.setProperty('--secondary-color', '$secondaryColor');" +
                    "javascript:document.documentElement.style.setProperty('--secondary-variant-color', '$secondaryVariantColor');" +
                    "javascript:document.documentElement.style.setProperty('--text-color', '$textColor');"
            web.loadUrl(jsCode)
        }
        // Handle interactions or add more views as needed
        binding.closeButton.setOnClickListener {
            dismiss() // Close the bottom sheet dialog
        }
    }


}

class EditWebActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditWebBinding

    private var imageUri: Uri? = null
    private lateinit var SaveBtn: Button
    private lateinit var CancelBtn: Button

    companion object {
        private var touchedImageId: String? = null
        private const val FILE_PICKER_REQUEST_CODE = 1
    }

    @SuppressLint("JavascriptInterface")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title="Edit Your Website"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding = ActivityEditWebBinding.inflate(layoutInflater)
        setContentView(binding.root)

        SaveBtn = binding.EditWebSaveBtn
        CancelBtn = binding.EditWebCancelBtn

        web = binding.webView
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
        web.settings.domStorageEnabled = true

        web.addJavascriptInterface(this, "AndroidInterface")

//        val baseUrl = "http://${ipaddress}/wda/templates/template1.html"
//        fontFamily = "Arial, sans-serif"
//        primaryVariantColor = R.color.RoseRed.toString()
//        secondaryColor  = R.color.RoseQuartz2.toString()
//        secondaryVariantColor = R.color.Pauce.toString()
//        textColor=R.color.black.toString()

//        contentEditableEnabled = !contentEditableEnabled
//        toggleContentEditable(contentEditableEnabled)

        val sp = getSharedPreferences("wda", MODE_PRIVATE)
        val Tpath = sp.getString("TemplatePath","")
        val baseUrl = "http://${ipaddress}$Tpath"
        var DomainName:String?

        val uri = Uri.parse(baseUrl)
            .buildUpon()
            .appendQueryParameter("ipaddress", ipaddress)
            .appendQueryParameter("primaryColor", ModalBottomSheet.primaryColor)
            .appendQueryParameter("primaryVariantColor", ModalBottomSheet.primaryVariantColor)
            .appendQueryParameter("secondaryColor", ModalBottomSheet.secondaryColor)
            .appendQueryParameter("secondaryVariantColor", ModalBottomSheet.secondaryVariantColor)
            .appendQueryParameter("fontFamily", ModalBottomSheet.fontFamily)
            .appendQueryParameter("textColor", ModalBottomSheet.textColor)
            .build()

        web.loadUrl(uri.toString())
        web.webChromeClient = WebChromeClient()

        web.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                SaveBtn.isEnabled = true
            }
        }
        CancelBtn.setOnClickListener {
            this@EditWebActivity.recreate()
        }

        val EditWebPrefrense = getSharedPreferences("wda", MODE_PRIVATE)
        val UID = EditWebPrefrense.getString("userId", "")
        val Uname = EditWebPrefrense.getString("UName", "")
        val UDOI = EditWebPrefrense.getString("UDOI", "")
        val UCOI = EditWebPrefrense.getString("UCOI", "")
        val UWebType = EditWebPrefrense.getString("WebsiteType", "")
        val UTAN = EditWebPrefrense.getString("UTAN", "")
        val WebsiteName=EditWebPrefrense.getString("WebsiteName","")
//        Save Button
        SaveBtn.setOnClickListener {
            web.evaluateJavascript(
                "(function() { return document.documentElement.outerHTML; })();"
            ) { content ->
                val task: String = intent.getStringExtra("Task").toString()
                if (task == "Register") {
                    CoroutineScope(Dispatchers.IO).launch {
                        withContext(Dispatchers.Main) {
                            val cleanedContent =
                                content.trim().removePrefix("\"").removeSuffix("\"")
                            val cleanedHtml = cleanedContent.replace("\\u003C", "<")
                                .replace("\\n", "")
                                .replace("\\", "")

                            val compressedHtml = compressGzip(cleanedHtml)
                            val base64Html = Base64.encodeToString(compressedHtml, Base64.DEFAULT)
                            withContext(Dispatchers.IO) {
                                val message = User.websiteRegister(
                                    UID.toString(),
                                    Uname.toString(),
                                    base64Html,
                                    UWebType.toString(),
                                    UDOI.toString(),
                                    UCOI.toString(),
                                    UTAN.toString(),
                                    " "
                                )
                                withContext(Dispatchers.Main){
                                    if(!message.getBoolean("success")){
                                        Toast.makeText(this@EditWebActivity, message.getString("message"), Toast.LENGTH_SHORT).show()
                                    }else{
                                        Toast.makeText(this@EditWebActivity, "Website Registered", Toast.LENGTH_SHORT).show()
                                        DomainName = "/wda/"+message.getString("domainName").replace("\\s+".toRegex(), "")+".html"
                                        val sp = getSharedPreferences("wda", MODE_PRIVATE)
                                        val editor = sp.edit()
                                        editor.putString("TemplatePath", DomainName)
                                        editor.apply()
                                        this@EditWebActivity.recreate()
                                    }
                                }
//
                                //editor.putString("Domain",Uname.toString())

//                            User.websiteRegister("65133ab2812fd50cec6e1760","Automobile",base64Html,"Organisational","16-09-2023","6484984894","4684646354","6846164836")
                            }
                        }
                    }
                } else {
                    contentEditableEnabled = !contentEditableEnabled
                    toggleContentEditable(contentEditableEnabled)

                    CoroutineScope(Dispatchers.IO).launch {
                        withContext(Dispatchers.Main) {
                            val cleanedContent =
                                content.trim().removePrefix("\"").removeSuffix("\"")
                            val cleanedHtml = cleanedContent.replace("\\u003C", "<")
                                .replace("\\n", "")
                                .replace("\\", "")

                            val compressedHtml = compressGzip(cleanedHtml)
                            val base64Html = Base64.encodeToString(compressedHtml, Base64.DEFAULT)
                            withContext(Dispatchers.IO) {
                                val message=User.updateWebsite(base64Html, WebsiteName.toString())
                                withContext(Dispatchers.Main){
                                    if (!message.getBoolean("success")){
                                        Toast.makeText(this@EditWebActivity, message.getString("message"), Toast.LENGTH_SHORT).show()
                                    }else{
                                        Toast.makeText(this@EditWebActivity, "Website Updated", Toast.LENGTH_SHORT).show()
                                        this@EditWebActivity.recreate()
                                    }
                                }
                            }
                        }
                    }
                }

            }
//            contentEditableEnabled = !contentEditableEnabled
//            disableContentEditable()
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
        @SuppressLint("SetJavaScriptEnabled")
        private fun toggleContentEditable(enabled: Boolean) {
            // JavaScript function to toggle contenteditable elements
            val jsCode = """
                function toggleContentEditable(enabled) {
                    var contentEditableElements = document.querySelectorAll('[contenteditable]');
                    contentEditableElements.forEach(function(element) {
                        element.contentEditable = enabled ? 'true' : 'false';
                    });
                }
                toggleContentEditable($contentEditableEnabled);
            """.trimIndent()
            Log.i("Project Errro","hii")
            // Execute the JavaScript code in the WebView
            web.evaluateJavascript(jsCode, null)
        }

        @JavascriptInterface
        fun showFileChooser(id: String) {
            touchedImageId = id
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
            if (resultCode != RESULT_CANCELED) {
                imageUri = data?.data!!

                val bitmap: Bitmap = MediaStore.Images.Media.getBitmap(contentResolver, imageUri)
                val byteArrayOutputStream = ByteArrayOutputStream()

                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)

                val byteArray: ByteArray = byteArrayOutputStream.toByteArray()

                val base64Image = Base64.encodeToString(byteArray, Base64.DEFAULT)

                CoroutineScope(Dispatchers.IO).launch {
                    var imageJSONData = User.addImages("Vaibhav Patel", touchedImageId, base64Image)
                    val jsCode = "setImages('$imageJSONData');"
                    GlobalScope.launch(Dispatchers.Main) {
                        web.evaluateJavascript(jsCode, null)
                    }
                }
            }
        }
    }
