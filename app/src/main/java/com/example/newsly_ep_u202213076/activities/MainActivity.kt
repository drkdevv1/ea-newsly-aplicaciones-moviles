package com.example.newsly_ep_u202213076.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.newsly_ep_u202213076.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val findNewsButton: Button = findViewById(R.id.findNewsButton)
        findNewsButton.setOnClickListener {
            val intent = Intent(this, FindNewsActivity::class.java)
            startActivity(intent)
        }
        val favoriteNewsButton: Button = findViewById(R.id.favoriteNewsButton)
        favoriteNewsButton.setOnClickListener {
            val intent = Intent(this, FavoriteNewsActivity::class.java)
            startActivity(intent)
        }

    }
}