package com.example.ScanPower.data

data class RequestParameters(
    val customer_zipcode: String,
    val page: String,
    val search_term: String,
    val type: String
)