package com.example.examenfirebase.api.repository

import com.example.examenfirebase.api.RetrofitInstance
import com.example.examenfirebase.retrofit.models.Data
import retrofit2.Call


class RepositoryField {
    suspend fun  getPost():Call<Data>{
      return RetrofitInstance.api.getFileDetail()
    }
}