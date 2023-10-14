package com.example.wda.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.example.wda.EditWebActivity
import com.example.wda.R
import com.example.wda.R.color.blue
import com.example.wda.model.Templates
import com.google.android.material.card.MaterialCardView
import pl.droidsonroids.gif.GifImageView

class SelectTemplateAdapter(private val activity: Activity, private val template : Array<Templates>, private val submitTempBtn : Button):
    ArrayAdapter<Templates>(activity,
    R.layout.activity_template_selection,template){
    @SuppressLint("SetTextI18n", "ResourceAsColor")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view: View? = convertView
        var viewHolder: ViewHolder


        if (view == null) {
            view = activity.layoutInflater.inflate(R.layout.selecttemplatecardgrid, parent, false)
            viewHolder = ViewHolder()
            viewHolder.TemplateCard=view.findViewById(R.id.TemplateCard)
            viewHolder.SelectTemplateName=view.findViewById(R.id.SelectTemplateName)
            viewHolder.SelectTemplateImage=view.findViewById(R.id.SelectTemplateImage)

            view.tag = viewHolder
        }else
            viewHolder = view.tag as ViewHolder

        Log.i("Template",template[position].TemplateName)
        viewHolder.TemplateCard.id=template[position].Id
        viewHolder.SelectTemplateName.text=template[position].TemplateName
        var gifUri="${activity.externalCacheDir?.absolutePath}/WebsiteImage/${template[position].TemplateImage}.gif"
        var tempPath=""
//        viewHolder.TemplateCard.isChecked=false
        Glide.with(activity)
            .asGif()
            .load(gifUri)
            .into(viewHolder.SelectTemplateImage)

        var TemplateArray = ArrayList<Int>()
        viewHolder.TemplateCard.setOnClickListener {
            TemplateArray.add(template[position].Id)
                viewHolder.TemplateCard.isChecked=true
//                viewHolder.TemplateCard.strokeWidth=2
//                viewHolder.TemplateCard.strokeColor= blue
            if (TemplateArray.size > 1){
                viewHolder.TempCard= view?.findViewById(TemplateArray[0])!!
//                Log.i("array", TemplateArray.toString())
                TemplateArray.removeAt(0)
                viewHolder.TempCard.isChecked=false
            }
            tempPath = template[position].TemplatePath
            val templatePathsp =activity.getSharedPreferences("wda", Context.MODE_PRIVATE)
            val editor = templatePathsp.edit()
            editor.putString("TemplatePath",tempPath)
            editor.apply()
        }

        submitTempBtn.setOnClickListener {
            val intent=Intent(activity,EditWebActivity::class.java)
            activity.startActivity(intent)
        }
        return view!!
    }

    companion object {
        class ViewHolder{
            lateinit var SelectTemplateName: TextView
            lateinit var SelectTemplateImage: GifImageView
            lateinit var TemplateCard: MaterialCardView
            lateinit var TempCard: MaterialCardView
        }
    }
}