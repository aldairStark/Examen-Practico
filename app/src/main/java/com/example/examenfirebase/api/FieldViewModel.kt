package com.example.examenfirebase.api

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examenfirebase.api.repository.RepositoryField
import com.example.examenfirebase.repository.data.network.Repository
import com.example.examenfirebase.retrofit.models.Data
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class FieldViewModel(private  val repository: RepositoryField):ViewModel(){
    val myResponse:MutableLiveData<Call<Data>> = MutableLiveData()

    fun getPost(){
        viewModelScope.launch {
            val response =repository.getPost()
            myResponse.value=response
        }
    }

}