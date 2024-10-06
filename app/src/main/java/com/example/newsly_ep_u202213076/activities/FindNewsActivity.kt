package com.example.newsly_ep_u202213076.activities

import Beans.News
import Interface.NewsPlaceHolder
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsly_ep_u202213076.adapters.NewsAdapter
import com.example.newsly_ep_u202213076.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FindNewsActivity : AppCompatActivity() {

    lateinit var service: NewsPlaceHolder
    lateinit var recycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_find_news)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        recycler = findViewById(R.id.recyclerNews)
        recycler.layoutManager = LinearLayoutManager(this)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://dev.formandocodigo.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(NewsPlaceHolder::class.java)
        getAllNews()

        val backButton: Button = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }
    }

    private fun getAllNews() {
        service.getArticles("google").enqueue(object : Callback<List<News>> {
            override fun onResponse(call: Call<List<News>>, response: Response<List<News>>) {
                val newsList = response.body() ?: emptyList()
                recycler.adapter = NewsAdapter(newsList) { news ->
                    val intent = Intent(this@FindNewsActivity, DetailsActivity::class.java).apply {
                        putExtra("sourceName", news.source.name)
                        putExtra("urlToImage", news.urlToImage)
                        putExtra("content", news.content)
                        putExtra("url", news.url)
                        putExtra("sourceId", news.source.id) // Agregar el ID de la fuente
                        putExtra("title", news.title) // Agregar el título
                        putExtra("author", news.author) // Agregar el autor
                        putExtra("description", news.description) // Agregar la descripción
                    }
                    startActivity(intent)
                }
            }

            override fun onFailure(call: Call<List<News>>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}