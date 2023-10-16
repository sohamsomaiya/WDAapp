package com.example.wda.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.wda.R
import com.example.wda.model.UserWebsite
import com.example.wda.model.Website

class DropdownWebNameAdapter (private val activity: Activity, private val objects: Array<UserWebsite>) :
    ArrayAdapter<UserWebsite>(activity, R.layout.dropdowngrid
        , objects) {
    @SuppressLint("SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view: View? = convertView
        val viewHolder: ViewHolder

        if (view == null) {
            view = activity.layoutInflater.inflate(R.layout.dropdowngrid, parent, false)
            viewHolder = ViewHolder()
            viewHolder.hiddenText = view.findViewById(R.id.HiddenTextSubjectId)
            viewHolder.WebsiteName = view.findViewById(R.id.dropDownItemText)

            view.tag = viewHolder
        } else
            viewHolder = view.tag as ViewHolder

        viewHolder.hiddenText.text = objects[position].WebsiteId
        viewHolder.WebsiteName.text = objects[position].WebsiteName

        return view!!
    }

    companion object {
        class ViewHolder {
            lateinit var hiddenText: TextView
            lateinit var WebsiteName: TextView
        }
    }
}