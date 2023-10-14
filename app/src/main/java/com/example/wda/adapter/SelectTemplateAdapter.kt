package com.example.wda.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.wda.R
import com.example.wda.model.Templates

class SelectTemplateAdapter(private val activity: Activity, private val template : Array<Templates>):
    ArrayAdapter<Templates>(activity,
    R.layout.selecttemplatecardgrid,template){
    @SuppressLint("SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view: View? = convertView
        var viewHolder: ViewHolder

        if (view == null) {
            view = activity.layoutInflater.inflate(R.layout.selecttemplatecardgrid, parent, false)
            viewHolder = ViewHolder()
            viewHolder.SelectTemplateName=view.findViewById(R.id.SelectTemplateName)
            viewHolder.SelectTemplateImage=view.findViewById(R.id.SelectTemplateImage)

            view.tag = viewHolder
        } else
            viewHolder = view.tag as ViewHolder

            viewHolder.SelectTemplateName.text=template[position].TemplateName
//            viewHolder.SelectTemplateImage

        return view!!
    }

    companion object {
        class ViewHolder{
            lateinit var SelectTemplateName: TextView
            lateinit var SelectTemplateImage: ImageView
        }
    }
}