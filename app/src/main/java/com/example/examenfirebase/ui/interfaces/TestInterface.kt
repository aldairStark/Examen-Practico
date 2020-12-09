package com.example.examenfirebase.ui.interfaces

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.examenfirebase.R
import com.example.examenfirebase.common.Constants
import com.example.examenfirebase.retrofit.ApiService
import com.example.examenfirebase.retrofit.models.Data
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class TestInterface : AppCompatActivity() {

    private lateinit var retrofit: Retrofit
    private lateinit var ApiService:ApiService
    private lateinit var call: Call<Data>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_interface)
    }
    private fun getFile(){

        retrofit =Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        ApiService=retrofit.create(ApiService::class.java)
    }
    fun downloadFileWithFixedUrl()= ApiService
}