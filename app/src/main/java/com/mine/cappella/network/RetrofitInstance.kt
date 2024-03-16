package com.mine.cappella.network

import com.mine.cappella.service.ApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitInstance {
    private
    const val BASE_URL = "http://ec2-35-165-186-112.us-west-2.compute.amazonaws.com/"
    val api: ApiService by lazy {
        val client = OkHttpClient.Builder().apply {
            connectTimeout(15,TimeUnit.SECONDS)
            readTimeout(15,TimeUnit.SECONDS)
        }.build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ApiService::class.java)
    }
}