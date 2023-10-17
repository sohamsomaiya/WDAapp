package com.example.wda.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.wda.R
import com.example.wda.model.ListOfWebsite

class ViewStatusAdapter(private val activity: Activity, private val objects: Array<ListOfWebsite>) :
    ArrayAdapter<ListOfWebsite>(activity, R.layout.viewstatusgrid
        , objects) {
    @SuppressLint("SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view: View? = convertView
        val viewHolder: ViewHolder

        if (view == null) {
            view = activity.layoutInflater.inflate(R.layout.viewstatusgrid, parent, false)
            viewHolder = ViewHolder()
            viewHolder.WebsiteName = view.findViewById(R.id.WebName)
            viewHolder.WebsiteId = view.findViewById(R.id.WebId)
            viewHolder.WebStatus = view.findViewById(R.id.WebStatus)
            viewHolder.DomainName = view.findViewById(R.id.WebDomain)

            view.tag = viewHolder
        } else
            viewHolder = view.tag as ViewHolder
        viewHolder.WebsiteId.text = "Website Id:- "+objects[position].WebsiteId
        viewHolder.WebsiteName.text = "Website Name:- "+objects[position].WebsiteName
        viewHolder.WebStatus.text = "Website Status:- "+objects[position].StatusName
        viewHolder.DomainName.text = "Domain Name:- "+objects[position].DomainName

//        fun getWebsiteId(position: Int): String {
//            return objects[position].WebsiteId!!
//        }

        return view!!
    }

    companion object {
        class ViewHolder {
            lateinit var WebsiteName: TextView
            lateinit var WebsiteId: TextView
            lateinit var DomainName: TextView
            lateinit var WebStatus: TextView
        }
    }
}