package com.example.examenfirebase.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.examenfirebase.repository.data.network.Repository
import com.example.examenfirebase.retrofit.models.RecyclerViewClass.EmployData

class EmployeesViewModel:ViewModel() {
    private val repository= Repository()


    fun fetchEmployeesData():LiveData<MutableList<EmployData>>{
        val mutableData =MutableLiveData<MutableList<EmployData>>()
        repository.getEmployData().observeForever { it ->
             mutableData.value = it
        }
        return mutableData
        }
    }
