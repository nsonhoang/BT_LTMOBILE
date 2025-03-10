package com.example.bai2_tuan2

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bai2_tuan2.databinding.FragmentHomeBinding


class Home : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    lateinit var customListView: CustomListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        setListViewBook()
        addEventsBtn()

        return binding.root
    }

    private fun setListViewBook() {
        val list = resources.getStringArray(R.array.list_book)
        customListView = CustomListView(requireActivity(), list)

        val listBook = binding.lvBook
        listBook.adapter=customListView
    }
    private fun addEventsBtn(){
        onClickAdd()
        onClickChange()
    }

    private fun onClickAdd() {
        binding.btnName.setOnClickListener {
            val staffFragment = Staff() // Tạo một instance của Staff Fragment
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame_layout, staffFragment) // R.id.fragment_container là ID của view chứa fragment
            transaction.addToBackStack(null) // Thêm vào back stack nếu bạn muốn quay lại
            transaction.commit() // Thực hiện giao dịch

        }
    }

    private fun onClickChange() {

    }
}