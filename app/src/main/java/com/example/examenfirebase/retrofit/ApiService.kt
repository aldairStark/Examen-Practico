package com.example.examenfirebase.retrofit

import com.example.examenfirebase.retrofit.models.Data
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {
   @GET("/s/5u21281sca8gj94/getFile.json?dl=0")
    fun downloadFileWithFixedUrl(): Call<Data>

    @GET("/s/5u21281sca8gj94/getFile.json?dl=0")
    fun getFileDetail(): Call<Data>

}