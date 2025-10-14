package com.svbsyucorp.damburguershop

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.svbsyucorp.damburguershop.adapters.OrdersAdapter
import com.svbsyucorp.damburguershop.databinding.ActivityOrdersBinding
import com.svbsyucorp.damburguershop.models.OrderModel

class OrdersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrdersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrdersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            finish()
        }

        val ordersList = listOf(
            OrderModel("Pedido Id #1", "14-10-2025", "S/ 22.10"),
            OrderModel("Pedido Id #2", "14-10-2025", "S/ 15.50"),
            OrderModel("Pedido Id #3", "14-10-2025", "S/ 31.00"),
            OrderModel("Pedido Id #4", "14-10-2025", "S/ 8.50")
        )

        val adapter = OrdersAdapter(this, ordersList)
        binding.ordersListView.adapter = adapter
    }
}
