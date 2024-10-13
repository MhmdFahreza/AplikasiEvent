package com.example.aplikasievent.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    // Base URL of the API
    private const val BASE_URL = "https://event-api.dicoding.dev/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())  // Convert JSON data using Gson
            .build()
    }

    // Access the API service
    val api: EventApiService by lazy {
        retrofit.create(EventApiService::class.java)
    }
}
