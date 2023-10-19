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
    private lateinit var web: WebView
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
        val sp = getSharedPreferences("wda", MODE_PRIVATE)
        val Tpath = sp.getString("TemplatePath","")
        val baseUrl = "http://${ipaddress}$Tpath"
        val uri = Uri.parse(baseUrl)
            .buildUpon()
            .appendQueryParameter("ipaddress", ipaddress)
            .build()

        web.loadUrl(uri.toString())
        web.webChromeClient = WebChromeClient()

        web.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                SaveBtn.isEnabled = true
            }
        }

        val EditWebPrefrense = getSharedPreferences("wda", MODE_PRIVATE)
        val UID = EditWebPrefrense.getString("userId", "")
        val Uname = EditWebPrefrense.getString("UName", "")
        val UDOI = EditWebPrefrense.getString("UDOI", "")
        val UCOI = EditWebPrefrense.getString("UCOI", "")
        val UWebType = EditWebPrefrense.getString("WebsiteType", "")
        val UTAN = EditWebPrefrense.getString("UTAN", "")
        SaveBtn.setOnClickListener {
            web.evaluateJavascript(
                "(function() { return document.documentElement.outerHTML; })();"
            ) { content ->
                CoroutineScope(Dispatchers.IO).launch {
                    withContext(Dispatchers.Main) {
                        val cleanedContent = content.trim().removePrefix("\"").removeSuffix("\"")
                        val cleanedHtml = cleanedContent.replace("\\u003C", "<")
                            .replace("\\n", "")
                            .replace("\\", "")

                        val compressedHtml = compressGzip(cleanedHtml)
                        val base64Html = Base64.encodeToString(compressedHtml, Base64.DEFAULT)
                        withContext(Dispatchers.IO) {
                            User.websiteRegister(UID.toString(),Uname.toString(),base64Html,UWebType.toString(),UDOI.toString(),UCOI.toString(),UTAN.toString()," ")
//                            User.websiteRegister("65133ab2812fd50cec6e1760","Automobile",base64Html,"Organisational","16-09-2023","6484984894","4684646354","6846164836")
                        }
                    }
                }
                CoroutineScope(Dispatchers.IO).launch {
                    withContext(Dispatchers.Main){
                        val cleanedContent = content.trim().removePrefix("\"").removeSuffix("\"")
                        val cleanedHtml = cleanedContent.replace("\\u003C", "<")
                            .replace("\\n", "")
                            .replace("\\", "")

                        val compressedHtml = compressGzip(cleanedHtml)
                        val base64Html = Base64.encodeToString(compressedHtml, Base64.DEFAULT)
                        withContext(Dispatchers.IO){
                            User.updateWebsite(base64Html,Uname.toString())
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
