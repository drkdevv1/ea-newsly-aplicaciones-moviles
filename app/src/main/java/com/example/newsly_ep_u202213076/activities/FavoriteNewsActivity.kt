package com.example.newsly_ep_u202213076.activities

import DB.AppDataBase
import Entity.FavoriteNew
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsly_ep_u202213076.adapters.FavoriteNewsAdapter
import com.example.newsly_ep_u202213076.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoriteNewsActivity : AppCompatActivity() {
    private lateinit var appDB: AppDataBase
    lateinit var favoriteNews: MutableList<FavoriteNew>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_favorite_news)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val backButton: Button = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }

        appDB = AppDataBase.getDatabase(this)
        val recycler: RecyclerView = findViewById(R.id.recyclerFavoriteNews)

        GlobalScope.launch {
            val news = appDB.favoriteNewsDAO().getAll()
            favoriteNews = news.toMutableList()
            withContext(Dispatchers.Main) {
                recycler.layoutManager = LinearLayoutManager(applicationContext)
                recycler.adapter = FavoriteNewsAdapter(favoriteNews)
            }
        }
    }
}