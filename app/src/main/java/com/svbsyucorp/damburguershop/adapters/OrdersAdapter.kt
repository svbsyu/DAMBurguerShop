package com.svbsyucorp.damburguershop.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.svbsyucorp.damburguershop.R
import com.svbsyucorp.damburguershop.models.OrderModel

class OrdersAdapter(context: Context, private val orders: List<OrderModel>) :
    ArrayAdapter<OrderModel>(context, 0, orders) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.order_item, parent, false)
        val currentOrder = getItem(position)

        val orderIdTextView = view.findViewById<TextView>(R.id.orderIdTextView)
        val orderFechaTextView = view.findViewById<TextView>(R.id.orderFechaTextView)
        val orderPrecioTextView = view.findViewById<TextView>(R.id.orderPrecioTextView)

        if (currentOrder != null) {
            orderIdTextView.text = currentOrder.id
            orderFechaTextView.text = "Fecha: ${currentOrder.fecha}"
            orderPrecioTextView.text = "Precio total: ${currentOrder.precio}"
        }

        return view
    }
}
