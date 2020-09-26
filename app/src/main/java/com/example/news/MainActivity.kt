package com.example.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.news.api.ApiInterface
import com.example.news.models.News
import com.example.news.models.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), NewsAdapter.Listener {


    private lateinit var processBar: ProgressBar
    private lateinit var recyclerView: RecyclerView
    private lateinit var apiInterface : ApiInterface
    private lateinit var retrofit: Retrofit

    val apiKey : String = "ba88909bbda0454aaa503a8fff5e1225"
    val country : String = "ru"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        processBar = findViewById(R.id.progress_bar)
        recyclerView = findViewById(R.id.news_recycler)

        retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiInterface = retrofit.create(ApiInterface::class.java)

        apiInterface.getNews(apiKey, country).enqueue(object : Callback<NewsResponse>{
            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                processBar.visibility = View.GONE
                Toast.makeText(this@MainActivity, "Ошибка запроса", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {

                if (response.isSuccessful){
                    processBar.visibility = View.GONE
                    if (response.body()?.articles != null){
                        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
                        recyclerView.adapter = NewsAdapter(response.body()?.articles!!, this@MainActivity)
                    }


                } else{
                    processBar.visibility = View.GONE
                    Toast.makeText(this@MainActivity, "Ошибка запроса", Toast.LENGTH_SHORT).show()
                }
            }

        })
    }

    override fun onItemClick(news: News) {

    }
}