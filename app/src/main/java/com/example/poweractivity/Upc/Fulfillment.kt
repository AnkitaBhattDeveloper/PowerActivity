package com.example.poweractivity.Upc

data class Fulfillment(
    val delivery_from_store: Boolean,
    val message: String,
    val pickup: Boolean,
    val shipping: Boolean,
    val zipcode: String
)