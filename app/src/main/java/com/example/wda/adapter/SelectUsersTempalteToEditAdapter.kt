package com.example.wda.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.wda.R
import com.example.wda.model.UserWebsite
import com.google.android.material.card.MaterialCardView
import com.google.android.material.divider.MaterialDivider

class SelectUsersTempalteToEditAdapter (private val activity: Activity, private val objects: Array<UserWebsite>) :
    ArrayAdapter<UserWebsite>(activity, R.layout.userstemplatetoeditgrid
        , objects) {
    private var selectedPosition = -1

    @SuppressLint("SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view: View? = convertView
        val viewHolder: ViewHolder

        if (view == null) {
            view = activity.layoutInflater.inflate(R.layout.userstemplatetoeditgrid, parent, false)
            viewHolder = ViewHolder()
            viewHolder.WebsiteType = view.findViewById(R.id.SelectTemplateType)
            viewHolder.WebsiteName = view.findViewById(R.id.SelectTemplateName)
            viewHolder.CorporateIdentificationNo = view.findViewById(R.id.SelectTemplateCOI)
            viewHolder.DateOfIncorporation = view.findViewById(R.id.SelectTemplateDOI)
            viewHolder.DomainName = view.findViewById(R.id.SelectTemplateDomainName)
            viewHolder.LogoImage = view.findViewById(R.id.SelectTemplateImage)
            viewHolder.DividerDoi = view.findViewById(R.id.DividerSelectTemplateDOI)
            viewHolder.DividerCoi = view.findViewById(R.id.DividerSelectTemplateCOI)
            viewHolder.WebCard = view.findViewById(R.id.WebCard)
            viewHolder.DateOfIncorporation.visibility = View.GONE
            viewHolder.CorporateIdentificationNo.visibility = View.GONE
            viewHolder.DividerDoi.visibility = View.GONE
            viewHolder.DividerCoi.visibility = View.GONE
//            if (objects[position].WebsiteType=="Organisational") {
//                viewHolder.DateOfIncorporation.visibility = View.VISIBLE
//                viewHolder.CorporateIdentificationNo.visibility = View.VISIBLE
//                viewHolder.DividerDoi.visibility = View.VISIBLE
//                viewHolder.DividerCoi.visibility = View.VISIBLE
//            }

            view.tag = viewHolder
        } else
            viewHolder = view.tag as ViewHolder

        viewHolder.WebsiteName.text = "Webiste Name:- "+objects[position].WebsiteName
        viewHolder.WebsiteType.text = "Webiste Type:- "+objects[position].WebsiteType
        viewHolder.DomainName.text = "Domain Name:- "+objects[position].DomainName
        viewHolder.DateOfIncorporation.text = "DOI :- "+objects[position].DateOfIncorporation
        viewHolder.CorporateIdentificationNo.text = "COI:- "+objects[position].CorporateIdentificationNo
        viewHolder.LogoImage.setBackgroundResource(R.drawable.templatesample)
        viewHolder.WebCard.isChecked = (position == selectedPosition)
        viewHolder.WebCard.setOnClickListener {
            if (position != selectedPosition) {
                if (selectedPosition != -1) {
                    // Deselect the previously selected card
                    val previousSelectedView = parent.getChildAt(selectedPosition)
                    if (previousSelectedView != null) {
                        val previousSelectedViewHolder =
                            previousSelectedView.tag as ViewHolder
                        previousSelectedViewHolder.WebCard.isChecked = false
                        previousSelectedViewHolder.WebCard.strokeWidth = 0

                    }
                }
                // Update the selected position to the current card
                selectedPosition = position
            }
            viewHolder.WebCard.isChecked = true
            viewHolder.WebCard.strokeWidth = 2
        }
        return view!!
    }

    companion object {
        class ViewHolder {
            lateinit var WebsiteName: TextView
            lateinit var WebsiteType: TextView
            lateinit var DateOfIncorporation: TextView
            lateinit var CorporateIdentificationNo: TextView
            lateinit var DomainName: TextView
            lateinit var LogoImage: ImageView
            lateinit var DividerDoi: MaterialDivider
            lateinit var DividerCoi: MaterialDivider
            lateinit var WebCard: MaterialCardView
        }
    }
}