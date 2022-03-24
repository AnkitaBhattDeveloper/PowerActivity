package com.example.poweractivity.Upc

data class Variant(
    val criteria: List<Criteria>,
    val images: List<ImageX>,
    val in_stock: Boolean,
    val item_id: String,
    val link: String,
    val price: Double,
    val product_id: String
)