package com.example.newsly_ep_u202213076.viewholders

import DB.AppDataBase
import Entity.FavoriteNew
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsly_ep_u202213076.R
import com.example.newsly_ep_u202213076.adapters.FavoriteNewsAdapter
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoriteNewsViewHolder(view: View, private val adapter: FavoriteNewsAdapter) : RecyclerView.ViewHolder(view) {
    private val newsImage: ImageView = view.findViewById(R.id.imgFavoriteNews)
    private val newsTitle: TextView = view.findViewById(R.id.txtFavoriteTitle)
    private val newsAuthor: TextView = view.findViewById(R.id.txtFavoriteAuthor)
    private val newsDescription: TextView = view.findViewById(R.id.txtFavoriteDescription)
    private val deleteButton: ImageButton = view.findViewById(R.id.btnDeleteFavorite)

    fun render(favoriteNew: FavoriteNew) {
        newsTitle.text = favoriteNew.title
        newsAuthor.text = favoriteNew.author
        newsDescription.text = favoriteNew.description
        Picasso.get().load(favoriteNew.urlToImage).into(newsImage)

        deleteButton.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
                val db = AppDataBase.getDatabase(itemView.context)
                db.favoriteNewsDAO().delete(favoriteNew)
                withContext(Dispatchers.Main) {
                    adapter.removeItem(adapterPosition)
                }
            }
        }
    }
}