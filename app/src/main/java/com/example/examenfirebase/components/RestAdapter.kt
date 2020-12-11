package com.example.examenfirebase.components

import com.example.examenfirebase.common.Constants
import com.google.android.gms.common.api.Api
import com.squareup.okhttp.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestAdapter {
    companion object {


    fun getRestAdapter():Retrofit{
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = okhttp3.OkHttpClient.Builder().addInterceptor(interceptor).build()
        val retrofit=Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        return retrofit
    }
    }
}