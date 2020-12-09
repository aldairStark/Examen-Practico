package com.example.examenfirebase.retrofit

import com.example.examenfirebase.retrofit.models.Data
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET


interface ApiService {
    @GET("getFile.json?dl=0")
    fun downloadFileWithFixedUrl(): Call<Data>
    @GET("getFile")
    fun getFileDetail(): Call<Data>
}