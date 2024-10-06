package com.example.newsly_ep_u202213076.adapters

import Entity.FavoriteNew
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsly_ep_u202213076.viewholders.FavoriteNewsViewHolder
import com.example.newsly_ep_u202213076.R

class FavoriteNewsAdapter(private val favoriteNews: MutableList<FavoriteNew>) : RecyclerView.Adapter<FavoriteNewsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteNewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return FavoriteNewsViewHolder(layoutInflater.inflate(R.layout.card_favorite_news, parent, false), this)
    }

    override fun getItemCount(): Int = favoriteNews.size

    override fun onBindViewHolder(holder: FavoriteNewsViewHolder, position: Int) {
        val item = favoriteNews[position]
        holder.render(item)
    }

    fun removeItem(position: Int) {
        favoriteNews.removeAt(position)
        notifyItemRemoved(position)
    }
}