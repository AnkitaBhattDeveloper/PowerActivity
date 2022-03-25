package com.example.ScanPower.Upc

data class BuyboxWinner(
    val availability: Availability,
    val currency_symbol: String,
    val fulfillment: Fulfillment,
    val id: String,
    val offers_total: Int,
    val price: Double,
    val seller: Seller,
    val unit_price: Double
)