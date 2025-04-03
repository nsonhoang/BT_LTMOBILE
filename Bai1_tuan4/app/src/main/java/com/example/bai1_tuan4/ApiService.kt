package com.example.bai1_tuan4

import ApiResponse
import ApiResponse2
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("tasks")
    suspend fun getTasks(): Response<ApiResponse>

    @GET("task/{id}")
    suspend fun getTaskData(@Path("id")id: Int): Response<ApiResponse2>
}