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
import com.example.wda.EditWebActivity
import com.example.wda.R
import com.example.wda.model.Templates
import com.google.android.material.card.MaterialCardView
import pl.droidsonroids.gif.GifImageView

class SelectTemplateAdapter(private val activity: Activity, private val template : Array<Templates>,private val TemplateImage : Unit, private val submitTempBtn : Button):
    ArrayAdapter<Templates>(activity,
    R.layout.activity_template_selection,template){
    @SuppressLint("SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view: View? = convertView
        var viewHolder: ViewHolder


        if (view == null) {
            view = activity.layoutInflater.inflate(R.layout.selecttemplatecardgrid, parent, false)
            viewHolder = ViewHolder()
            viewHolder.TemplateCard=view.findViewById(R.id.TemplateCard)
            viewHolder.SelectTemplateName=view.findViewById(R.id.SelectTemplateName)
            viewHolder.SelectTemplateImage=view.findViewById(R.id.SelectTemplateImage)
            var TemplateArray = ArrayList<Int>()
            viewHolder.TemplateCard.id=template[position].Id
            viewHolder.SelectTemplateName.text=template[position].TemplateName
//            viewHolder.SelectTemplateImage.setImageURI(template[position].TemplateImage.toUri())
            var tempPath=" "

            viewHolder.TemplateCard.setOnClickListener {
                TemplateArray.add(viewHolder.TemplateCard.id)
//                viewHolder.TempCard=view.findViewById(TemplateArray[0])
//                viewHolder.TempCard.isChecked=false
                if (TemplateArray.size == 2){
                    TemplateArray.removeFirst()
                }
                tempPath = template[position].TemplatePath
            }
            submitTempBtn.setOnClickListener {
                val intent=Intent(activity,EditWebActivity::class.java)
                activity.startActivity(intent)
                intent.putExtra("TempPath",tempPath)
            }
            view.tag = viewHolder
        }else {
            viewHolder = view.tag as ViewHolder
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