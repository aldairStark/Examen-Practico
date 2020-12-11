package com.example.examenfirebase.api

import com.example.examenfirebase.common.Constants
import com.example.examenfirebase.retrofit.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val  retrofit by lazy {
        Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

    }
    val api: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}