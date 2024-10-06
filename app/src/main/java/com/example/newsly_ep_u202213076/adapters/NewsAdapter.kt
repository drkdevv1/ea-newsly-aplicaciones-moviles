package com.example.newsly_ep_u202213076.adapters

import Beans.News
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsly_ep_u202213076.viewholders.NewsViewHolder
import com.example.newsly_ep_u202213076.R

class NewsAdapter(
    private val newsList: List<News>,
    private val clickListener: (News) -> Unit) : RecyclerView.Adapter<NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return NewsViewHolder(layoutInflater.inflate(R.layout.card_news, parent, false))
    }

    override fun getItemCount(): Int = newsList.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = newsList[position]
        holder.render(item, onDetailsClick = clickListener)
    }
}