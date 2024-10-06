package com.example.newsly_ep_u202213076.viewholders

import Beans.News
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsly_ep_u202213076.R
import com.squareup.picasso.Picasso

class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val newsTitle: TextView = view.findViewById(R.id.txtTitle)
    private val newsAuthor: TextView = view.findViewById(R.id.txtAuthor)
    private val newsPublishedAt: TextView = view.findViewById(R.id.txtPublishedAt)
    private val newsImage: ImageView = view.findViewById(R.id.imgFavoriteNews)
    private val detailsButton: Button = view.findViewById(R.id.btnDetails)

    fun render(newsModel: News, onDetailsClick: (News) -> Unit) {
        newsTitle.text = newsModel.title
        newsAuthor.text = newsModel.author
        newsPublishedAt.text = newsModel.publishedAt.toString()
        Picasso.get().load(newsModel.urlToImage)
            .resize(300, 300)
            .centerCrop().into(newsImage)

        detailsButton.setOnClickListener {
            onDetailsClick(newsModel)
        }
    }
}