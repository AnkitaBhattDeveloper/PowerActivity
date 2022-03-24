package com.example.poweractivity.data

data class FulfillmentX(
    val delivery_from_store: Boolean,
    val pickup: Boolean,
    val shipping: Boolean,
    val shipping_days: Int
)