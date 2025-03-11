package com.example.bai2_tuan2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel: ViewModel() {
    private val _nameStaff = MutableLiveData<String>()

    val nameStaff: LiveData<String> = _nameStaff

    fun setName(string: String) {
        _nameStaff.value = string
    }

}