package com.example.examenfirebase.retrofit

import android.provider.ContactsContract
import com.example.examenfirebase.retrofit.models.CatJson
import com.example.examenfirebase.retrofit.models.Data
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET


interface ApiService {
   @GET("/s/5u21281sca8gj94/getFile.json?dl=0")
    fun downloadFileWithFixedUrl(): Call<Data>

    @GET("/s/5u21281sca8gj94/getFile.json?dl=0")
    fun getFileDetail(): Call<List<Data>>


    @GET( "/facts/random")
    fun getCts(): Call<CatJson>
}