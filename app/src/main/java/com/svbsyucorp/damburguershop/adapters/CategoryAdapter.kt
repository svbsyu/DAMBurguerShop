package com.svbsyucorp.damburguershop.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.svbsyucorp.damburguershop.R
import com.svbsyucorp.damburguershop.models.CategoryModel

class CategoryAdapter(
    private val categories: List<CategoryModel>,
    private val onCategoryClick: (CategoryModel) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private var selectedPosition = -1
    private var lastSelectedPosition = -1

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleText: TextView = itemView.findViewById(R.id.titleCat)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        holder.titleText.text = category.title

        // Configurar estado seleccionado
        if (selectedPosition == position) {
            holder.titleText.setBackgroundResource(R.drawable.dark_brown_bg)
            holder.titleText.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.white))
        } else {
            holder.titleText.setBackgroundResource(R.drawable.white_bg)
            holder.titleText.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.darkBrown))
        }

        holder.itemView.setOnClickListener {
            lastSelectedPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
            onCategoryClick(category)
        }
    }

    override fun getItemCount(): Int = categories.size
}