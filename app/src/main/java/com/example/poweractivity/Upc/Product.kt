package com.example.poweractivity.Upc

data class Product(
    val brand: String,
    val breadcrumbs: List<Breadcrumb>,
    val buybox_winner: BuyboxWinner,
    val description: String,
    val description_full: String,
    val description_full_html: String,
    val description_html: String,
    val dimensions: String,
    val images: List<Image>,
    val item_id: String,
    val item_number: String,
    val link: String,
    val main_image: MainImage,
    val manufacturer: String,
    val model: String,
    val product_id: String,
    val rating: Double,
    val ratings_total: Int,
    val specifications: ArrayList<Specification>,
    val title: String,
    val type: String,
    val upc: String,
    val variants: List<Variant>
)