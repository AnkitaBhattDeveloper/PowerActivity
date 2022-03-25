package com.example.ScanPower.Upc

data class RequestParameters(
    val customer_zipcode: String,
    val item_id: String,
    val type: String
)