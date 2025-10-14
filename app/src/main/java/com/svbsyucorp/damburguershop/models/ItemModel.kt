package com.svbsyucorp.damburguershop.models

data class ItemModel(
    val categoryId: String,
    val description: String,
    val extra: String,
    val picUrl: List<String>,
    val price: Double,
    val rating: Double,
    val title: String
)