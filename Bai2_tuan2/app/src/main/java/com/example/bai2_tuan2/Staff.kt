package com.example.bai2_tuan2

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.bai2_tuan2.databinding.FragmentStaffBinding


class Staff : Fragment() {
    private lateinit var binding: FragmentStaffBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStaffBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        val view=binding.root
        addEvents()
        return view
    }

    private fun addEvents() {
        GlobalList.list = listOfStaff()
        setListViewStaff()
        handleAddStaff()
        onClickItemListStaff()
    }

    private fun handleAddStaff() {
        binding.btnLuu.setOnClickListener {
            val name:String = binding.edtTen.text.toString()
            GlobalList.list.add(name)
            binding.edtTen.setText("")
            setListViewStaff()
        }
    }
    private fun listOfStaff(): MutableList<String>{
        val list = resources.getStringArray(R.array.list_staff)
        return list.toMutableList()
    }

    private fun setListViewStaff() {
        binding.lvStaff.adapter = ArrayAdapter(
            requireContext(),android.R.layout.simple_list_item_1,GlobalList.list)

    }
    private fun onClickItemListStaff(){
        binding.lvStaff.onItemClickListener=AdapterView.OnItemClickListener { adapterView, view, i, l ->

        }
    }
}