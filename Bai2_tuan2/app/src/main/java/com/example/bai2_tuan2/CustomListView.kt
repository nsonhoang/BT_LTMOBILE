package com.example.bai2_tuan2

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox

class CustomListView(val activity: Activity, val list: Array<String>): ArrayAdapter<String>(activity,R.layout.item_checkbox) {

    private val checkItems = BooleanArray(list.size)

    override fun getCount(): Int {
        return list.size
    }
    fun getChecksItems(): BooleanArray{
        return checkItems
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val context = activity.layoutInflater
        val rowView= context.inflate(R.layout.item_checkbox,parent,false)
        val checkbox = rowView.findViewById<CheckBox>(R.id.checkBox)

        checkbox.text=list[position].toString()
        checkbox.isChecked=checkItems[position]

        checkbox.setOnCheckedChangeListener{_,isChecked ->
            checkItems[position] =isChecked
        }

        return rowView
    }
}