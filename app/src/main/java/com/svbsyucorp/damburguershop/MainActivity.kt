package com.svbsyucorp.damburguershop

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.svbsyucorp.damburguershop.adapters.BannerAdapter
import com.svbsyucorp.damburguershop.adapters.CategoryAdapter
import com.svbsyucorp.damburguershop.adapters.PopularAdapter
import com.svbsyucorp.damburguershop.models.BannerModel
import com.svbsyucorp.damburguershop.models.CategoryModel
import com.svbsyucorp.damburguershop.models.ItemModel
import java.util.Timer
import java.util.TimerTask

class MainActivity : AppCompatActivity() {

    private lateinit var bannerViewPager: ViewPager2
    private lateinit var timer: Timer
    private val bannerUrls = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        initViews()
        loadData()
        setupBottomNavigation()
    }

    private fun initViews() {
        bannerViewPager = findViewById(R.id.bannerViewPager)
    }

    private fun loadData() {
        try {
            // Datos de ejemplo basados en tu JSON
            val categories = listOf(
                CategoryModel(0, "Hamburguesa"),
                CategoryModel(1, "Burrito"),
                CategoryModel(2, "Pizza"),
                CategoryModel(3, "Brocheta"),
                CategoryModel(4, "Fusion")
            )

            val popularItems = listOf(
                ItemModel(
                    categoryId = "4",
                    description = "Crispers crujientes hechos a mano sobre un mix de lechugas, pico de gallo, palta, culantro, tiras de tortilla, aderezo ranch y salsa santa fe.",
                    extra = "Acevichada",
                    picUrl = listOf("https://res.cloudinary.com/dkauxesya/image/upload/v1760165943/food7_geib16.png"),
                    price = 4.5,
                    rating = 4.6,
                    title = "Santa Fe Crisper Salad"
                ),
                ItemModel(
                    categoryId = "0",
                    description = "Imagina una hamburguesa vegana que encanta los sentidos con su mezcla única de sabores y texturas.",
                    extra = "Acevichada, Double Meat",
                    picUrl = listOf("https://res.cloudinary.com/dkauxesya/image/upload/v1760165943/food1_mn5tkz.png"),
                    price = 3.5,
                    rating = 4.0,
                    title = "Hamburguesa"
                ),
                ItemModel(
                    categoryId = "2",
                    description = "La pizza de pepperoni es un clásico que a muchos les encanta, pero ¿alguna vez has probado agregar orégano fresco?.",
                    extra = "Queso, Pepperoni",
                    picUrl = listOf("https://res.cloudinary.com/dkauxesya/image/upload/v1760165943/food9_gj8fwe.png"),
                    price = 8.0,
                    rating = 4.1,
                    title = "Pizza"
                ),
                ItemModel(
                    categoryId = "1",
                    description = "Sub Boneless Mango Habanero: Nuevo sándwich con salsa de mango habanero, cebollitas crujientes, boneless de pollo y ingredientes frescos.",
                    extra = "Crispy Spicy",
                    picUrl = listOf("https://res.cloudinary.com/dkauxesya/image/upload/v1760165943/food3_e4ze6z.png"),
                    price = 5.5,
                    rating = 4.4,
                    title = "Sandwich Plant-Based"
                )
            )

            val banners = listOf(
                "https://res.cloudinary.com/dkauxesya/image/upload/v1760165943/logo_opcional_mzhb9m.jpg"
            )

            setupBanner(banners)
            setupCategories(categories)
            setupPopularItems(popularItems)
        } catch (e: Exception) {
            Toast.makeText(this, "Error cargando datos: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupBanner(banners: List<String>) {
        bannerUrls.clear()
        bannerUrls.addAll(banners)

        val adapter = BannerAdapter(bannerUrls)
        bannerViewPager.adapter = adapter

        findViewById<View>(R.id.progressBarBanner).visibility = View.GONE

        if (bannerUrls.size > 1) {
            autoScrollBanner()
        }
    }

    private fun setupCategories(categories: List<CategoryModel>) {
        val recyclerView = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recyclerViewCategory)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val adapter = CategoryAdapter(categories) { category ->
            Toast.makeText(this, "Categoría seleccionada: ${category.title}", Toast.LENGTH_SHORT).show()
        }
        recyclerView.adapter = adapter

        findViewById<View>(R.id.progressBarCategory).visibility = View.GONE
    }

    private fun setupPopularItems(items: List<ItemModel>) {
        val recyclerView = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recyclerViewPopular)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        val adapter = PopularAdapter(items) { item ->
            Toast.makeText(this, "Item seleccionado: ${item.title}", Toast.LENGTH_SHORT).show()
        }
        recyclerView.adapter = adapter

        findViewById<View>(R.id.progressBarPopular).visibility = View.GONE
    }

    private fun setupBottomNavigation() {
        findViewById<View>(R.id.explorerBtn).setOnClickListener {
            startActivity(Intent(this, ExplorarActivity::class.java))
        }

        findViewById<View>(R.id.cartBtn).setOnClickListener {
            Toast.makeText(this, "Carrito", Toast.LENGTH_SHORT).show()
        }

        findViewById<View>(R.id.favoriteBtn).setOnClickListener {
            startActivity(Intent(this, FavoritosActivity::class.java))
        }

        findViewById<View>(R.id.orderBtn).setOnClickListener {
            startActivity(Intent(this, OrdersActivity::class.java))
        }

        findViewById<View>(R.id.profileBtn).setOnClickListener {
            Toast.makeText(this, "Perfil", Toast.LENGTH_SHORT).show()
        }
    }

    private fun autoScrollBanner() {
        val handler = Handler(Looper.getMainLooper())
        val runnable = Runnable {
            val current = bannerViewPager.currentItem
            val next = if (current + 1 < bannerUrls.size) current + 1 else 0
            bannerViewPager.currentItem = next
        }

        timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(runnable)
            }
        }, 3000, 3000)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::timer.isInitialized) {
            timer.cancel()
        }
    }
}