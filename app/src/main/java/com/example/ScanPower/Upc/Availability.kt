package com.example.ScanPower.Upc

data class Availability(
    val in_stock: Boolean,
    val preorder: Boolean,
    val raw: String
)