package com.example.news.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class NewsResponse(
        @SerializedName("status") var status : String? = null,
        @SerializedName("totalResults")  var totalResults : Int? = null,
        @SerializedName("articles")  var articles : ArrayList<News>? = null
)