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
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         val name = arguments?.getString("name")?:""
        val  arrayBook = arguments?.getStringArrayList("bookname")?:ArrayList()
        createListLibrary(arrayBook,name)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLibraryBookBinding.inflate(inflater,container,false)
        val view = binding.root

        return view
    }
    private fun createListLibrary(list: ArrayList<String>,name:String){
        val listView = mutableListOf<DataLibrary>()

        list.forEach { item->
            listView.add(DataLibrary(name,item))
        }

        val adapter = CustomListView2(requireActivity(),listView)

        binding.listLibrary.adapter=adapter


    }

}