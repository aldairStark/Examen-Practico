package com.example.examenfirebase.ui.interfaces

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.examenfirebase.R
import com.example.examenfirebase.common.Constants
import com.example.examenfirebase.components.RestAdapter
import com.example.examenfirebase.retrofit.ApiService
import com.example.examenfirebase.retrofit.models.Data
import com.example.examenfirebase.ui.Employees.Employees
import com.example.examenfirebase.ui.location.Location
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_test_interface.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.log

class TestInterface : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_interface)

        //getCurrentData()
        btnApiRest.setOnClickListener { v ->
            callServices()
               // getCurrentData()
        }
    }

    private fun callServices() {
        val userService: ApiService = RestAdapter.getRestAdapter().create(ApiService::class.java)
        val result: Call<Data> = userService.downloadFileWithFixedUrl()

        result.enqueue(object : Callback<Data> {
            override fun onFailure(call: Call<Data>, t: Throwable) {

                Toast.makeText(this@TestInterface,"Error",Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Data>, response: Response<Data>) {
                Toast.makeText(this@TestInterface,"ok",Toast.LENGTH_SHORT).show()

            }

        })
    }

}