package com.svbsyucorp.damburguershop

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FavoritosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_favoritos)

        setupBackButton()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.favoriteLayout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupBackButton() {
        findViewById<android.view.View>(R.id.backBtn).setOnClickListener {
            finish()
        }
    }
}
