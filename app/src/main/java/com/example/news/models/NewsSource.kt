package com.example.news.models
import com.google.gson.annotations.SerializedName

data class NewsSource(
        @SerializedName("id") var id : String? = null,
        @SerializedName("name") var name : String? = null
)
