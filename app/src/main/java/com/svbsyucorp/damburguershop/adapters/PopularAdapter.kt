package com.svbsyucorp.damburguershop.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.svbsyucorp.damburguershop.R
import com.svbsyucorp.damburguershop.models.ItemModel

class PopularAdapter(
    private val items: List<ItemModel>,
    private val onItemClick: (ItemModel) -> Unit
) : RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {

    inner class PopularViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.pic)
        val titleText: TextView = itemView.findViewById(R.id.titleTxt)
        val priceText: TextView = itemView.findViewById(R.id.priceTxt)
        val addButton: ImageView = itemView.findViewById(R.id.addBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_popular, parent, false)
        return PopularViewHolder(view)
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        val item = items[position]
        
        holder.titleText.text = item.title
        holder.priceText.text = "$${item.price}"

        // Cargar imagen con Glide
        if (item.picUrl.isNotEmpty()) {
            Glide.with(holder.itemView.context)
                .load(item.picUrl[0])
                .placeholder(R.drawable.placeholder_banner)
                .into(holder.imageView)
        }

        holder.itemView.setOnClickListener {
            onItemClick(item)
        }

        holder.addButton.setOnClickListener {
            onItemClick(item)
        }
    }

    override fun getItemCount(): Int = items.size
}