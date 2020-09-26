package com.example.news.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppFactory {

    private lateinit var retrofit : Retrofit
    var apiInterface : ApiInterface? = null

    fun getRetrofit1() : Retrofit{
        retrofit = Retrofit.Builder()
                .baseUrl("https://newsapi.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        return retrofit
    }
}