package com.example.wda

import MyBottomSheetFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wda.databinding.ActivityEditWebBinding
import com.example.wda.databinding.FragmentBottom2Binding
import com.example.wda.databinding.FragmentBottomBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ModalBottomSheet : BottomSheetDialogFragment() {
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

        // Handle interactions or add more views as needed
        binding.closeButton.setOnClickListener {
            dismiss() // Close the bottom sheet dialog
        }
    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }
}

class ModalBottomSheet2 : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentBottom2Binding
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

        // Handle interactions or add more views as needed
        binding.closeButton.setOnClickListener {
            dismiss() // Close the bottom sheet dialog
        }
    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }
}

class EditWebActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditWebBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
}