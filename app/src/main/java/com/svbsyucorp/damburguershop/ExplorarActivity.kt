package com.svbsyucorp.damburguershop

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.svbsyucorp.damburguershop.adapters.PopularAdapter
import com.svbsyucorp.damburguershop.models.ItemModel

class ExplorarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_explorar)

        setupBackButton()
        setupButtons()
        loadPopulares()
        loadNuevos()
    }

    private fun setupBackButton() {
        findViewById<View>(R.id.backBtn).setOnClickListener {
            finish()
        }
    }

    private fun loadPopulares() {
        findViewById<View>(R.id.progressBar).visibility = View.VISIBLE

        val popularItems = listOf(
            ItemModel(
                categoryId = "0",
                description = "Hamburguesa clásica con carne jugosa, lechuga, tomate y queso",
                extra = "Clásica",
                picUrl = listOf("https://res.cloudinary.com/dkauxesya/image/upload/v1760165943/food1_mn5tkz.png"),
                price = 8.5,
                rating = 4.8,
                title = "Hamburguesa Clásica"
            ),
            ItemModel(
                categoryId = "0",
                description = "Hamburguesa con doble carne y queso derretido",
                extra = "Doble Carne",
                picUrl = listOf("https://res.cloudinary.com/dkauxesya/image/upload/v1760165943/food1_mn5tkz.png"),
                price = 12.0,
                rating = 4.9,
                title = "Big Burger"
            )
        )

        val recyclerView = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recyclerPopulares)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = PopularAdapter(popularItems) { item ->
            Toast.makeText(this, "Seleccionaste: ${item.title}", Toast.LENGTH_SHORT).show()
        }

        findViewById<View>(R.id.progressBar).visibility = View.GONE
    }

    private fun loadNuevos() {
        val newItems = listOf(
            ItemModel(
                categoryId = "0",
                description = "Nueva hamburguesa vegana con ingredientes frescos",
                extra = "Vegana",
                picUrl = listOf("https://res.cloudinary.com/dkauxesya/image/upload/v1760165943/food1_mn5tkz.png"),
                price = 9.5,
                rating = 4.5,
                title = "Veggie Deluxe"
            ),
            ItemModel(
                categoryId = "0",
                description = "Hamburguesa BBQ con salsa especial y cebolla caramelizada",
                extra = "BBQ",
                picUrl = listOf("https://res.cloudinary.com/dkauxesya/image/upload/v1760165943/food1_mn5tkz.png"),
                price = 11.0,
                rating = 4.7,
                title = "BBQ Master"
            )
        )

        val recyclerView = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recyclerNuevos)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = PopularAdapter(newItems) { item ->
            Toast.makeText(this, "Seleccionaste: ${item.title}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupButtons() {
        findViewById<View>(R.id.familiaBtn).setOnClickListener {
            Toast.makeText(this, "Para una persona", Toast.LENGTH_SHORT).show()
        }

        findViewById<View>(R.id.rapidoBtn).setOnClickListener {
            Toast.makeText(this, "Recomendaciones para la familia 🍔", Toast.LENGTH_SHORT).show()
        }
    }
}