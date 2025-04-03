package com.example.bai1_tuan4


import Subtask
import TaskData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    val api : ApiService = RetrofitInstance.api

    private val _tasks = MutableStateFlow<List<TaskData>>(emptyList())
    val tasks: StateFlow<List<TaskData>> get() = _tasks

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage


    fun fetchTasks() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = api.getTasks()
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    _tasks.value = apiResponse?.data ?: emptyList()
                    _errorMessage.value = null
                } else {
                    _errorMessage.value = "Error: ${response.code()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
    private val _datatask = MutableStateFlow<TaskData?>(null)
    val datatask: StateFlow<TaskData?> get() = _datatask


    fun fetchTaskData(taskId: Int) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = api.getTaskData(taskId)
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    _datatask.value = apiResponse?.data
                } else {
                    _errorMessage.value = "Error: ${response.code()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
}