package com.example.wda.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.wda.R
import com.example.wda.model.Queries

class ViewQueryAdapter(private val activity: Activity, private val objects: Array<Queries>) :
    ArrayAdapter<Queries>(activity, R.layout.dropdowngrid
        , objects) {
    @SuppressLint("SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view: View? = convertView
        val viewHolder: ViewHolder

        if (view == null) {
            view = activity.layoutInflater.inflate(R.layout.dropdowngrid, parent, false)
            viewHolder = ViewHolder()
            viewHolder.WebsiteName = view.findViewById(R.id.dropDownItemText)
            viewHolder.WebsiteId = view.findViewById(R.id.WebsiteIdTxt)
            viewHolder.QueryDate = view.findViewById(R.id.QueryDate)
            viewHolder.QueryDescription = view.findViewById(R.id.QueryDescription)

            view.tag = viewHolder
        } else
            viewHolder = view.tag as ViewHolder

        viewHolder.WebsiteId.text = objects[position].WebsiteId
        viewHolder.QueryDate.text = objects[position].Date
        viewHolder.WebsiteName.text = objects[position].WebsiteName
        viewHolder.QueryDescription.text = objects[position].Description

//        fun getWebsiteId(position: Int): String {
//            return objects[position].WebsiteId!!
//        }

        return view!!
    }

    companion object {
        class ViewHolder {
            lateinit var WebsiteName: TextView
            lateinit var WebsiteId: TextView
            lateinit var QueryDate: TextView
            lateinit var QueryDescription: TextView
        }
    }
}