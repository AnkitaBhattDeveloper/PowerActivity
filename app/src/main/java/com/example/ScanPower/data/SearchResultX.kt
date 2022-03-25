package com.example.ScanPower.data

data class SearchResultX(
    val fulfillment: FulfillmentX,
    val inventory: InventoryX,
    val offers: OffersX,
    val position: Int,
    val product: ProductX
)