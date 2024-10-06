package com.example.newsly_ep_u202213076.activities

import DB.AppDataBase
import Entity.FavoriteNew
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.newsly_ep_u202213076.R
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailsActivity : AppCompatActivity() {
    private lateinit var appDB: AppDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_details)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val sourceName: TextView = findViewById(R.id.txtSourceName)
        val newsImage: ImageView = findViewById(R.id.imgNewsDetail)
        val content: TextView = findViewById(R.id.txtContent)
        val btnOpenWebsite: Button = findViewById(R.id.btnOpenWebsite)
        val btnFavorite: Button = findViewById(R.id.btnFavorite)

        val newsSourceId = intent.getStringExtra("sourceId")
        val newsSourceName = intent.getStringExtra("sourceName")
        val newsImageUrl = intent.getStringExtra("urlToImage")
        val newsContent = intent.getStringExtra("content")
        val newsUrl = intent.getStringExtra("url")
        val newsId = intent.getStringExtra("id")
        val newsTitle = intent.getStringExtra("title")
        val newsAuthor = intent.getStringExtra("author")
        val newsPublishedAt = intent.getStringExtra("publishedAt")
        val newsDescription = intent.getStringExtra("description")

        sourceName.text = newsSourceName
        content.text = newsContent

        Picasso.get().load(newsImageUrl)
            .placeholder(R.drawable.default_image)
            .into(newsImage)

        btnOpenWebsite.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(newsUrl))
            startActivity(browserIntent)
        }

        appDB = AppDataBase.getDatabase(this)
        val backButton: Button = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }


        btnFavorite.setOnClickListener {
            //Log.d("DetailsActivity", "Guardando noticia favorita: $newsSourceId, $newsPublishedAt, $newsAuthor, $newsTitle, $newsImageUrl, $newsDescription")
            val favoriteNew = FavoriteNew(
                id = newsSourceId ?: "",
                publishedAt = newsPublishedAt ?: "",
                author = newsAuthor ?: "",
                title = newsTitle ?: "",
                urlToImage = newsImageUrl ?: "",
                description = newsDescription ?: ""
            )
            GlobalScope.launch(Dispatchers.IO) {
                appDB.favoriteNewsDAO().insert(favoriteNew)
            }
        }
    }
}