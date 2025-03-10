package com.example.bai2_tuan2

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.bai2_tuan2.databinding.ItemListviewBinding

class CustomListView2(
    val activity: Activity,
    val list: List<DataLibrary>
) : ArrayAdapter<DataLibrary>(activity, R.layout.item_listview) {



    override fun getCount(): Int {
        return list.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val context= activity.layoutInflater
        val rowView= context.inflate(R.layout.item_listview,parent,false)


        val name= rowView.findViewById<TextView>(R.id.editTextText)

        val nameBook=rowView.findViewById<TextView>(R.id.editTextText2)

        name.setText(list[position].name)
        nameBook.setText(list[position].namebook)
        return rowView
    }
}