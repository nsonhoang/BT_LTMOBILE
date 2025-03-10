package com.example.bai2_tuan2

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox

class CustomListView(val activity: Activity, val list: Array<String>): ArrayAdapter<String>(activity,R.layout.item_checkbox) {

    override fun getCount(): Int {
        return list.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val context = activity.layoutInflater
        val rowView= context.inflate(R.layout.item_checkbox,parent,false)
        val checkbox = rowView.findViewById<CheckBox>(R.id.checkBox)

        checkbox.text=list[position].toString()

        return rowView
    }
}