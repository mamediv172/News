package com.example.news.api

import com.example.news.models.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {



    @GET("v2/top-headlines")
    fun getNews(@Query("apiKey") apiKey : String, @Query("country") country : String) : Call<NewsResponse>
}