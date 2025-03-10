package com.example.bai2_tuan2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bai2_tuan2.databinding.FragmentLibraryBookBinding

class LibraryBook : Fragment() {

    private lateinit var binding: FragmentLibraryBookBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLibraryBookBinding.inflate(inflater,container,false)
        val view = binding.root
        createListLibrary()

        return view
    }
    private fun createListLibrary(){
        var list= mutableListOf<DataLibrary>()
        list.add(DataLibrary("Nguễn Văn E","Chúa Tể Của Những Chiếc Nhẫn"))
        list.add(DataLibrary(" Nguyễn Văn D","Cuối Theo Chiều Gió"))
        list.add(DataLibrary("Lê Văn C","LOL"))

        val adapter = CustomListView2(requireActivity(),list)

        binding.listLibrary.adapter=adapter


    }

}