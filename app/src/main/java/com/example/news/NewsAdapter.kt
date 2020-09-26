package com.example.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news.models.News

class NewsAdapter(var list : ArrayList<News>, var listener: Listener) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)

        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.onBind(list[position], listener)
    }


    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var newsImage = itemView.findViewById(R.id.news_image) as ImageView
        var newsTitle = itemView.findViewById(R.id.news_title) as TextView
        var newsDescription = itemView.findViewById(R.id.news_description) as TextView

        fun onBind(news: News, listener : Listener){
            newsTitle.text = news.title
            newsDescription.text = news.description
            Glide.with(itemView.context).load(news.urlToImage).into(newsImage)
        }
    }

    interface Listener{
        fun onItemClick(news : News)
    }
}