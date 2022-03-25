package com.example.ScanPower.Upc

data class UpcModel(
    val location_info: LocationInfo,
    val product: Product,
    val request_info: RequestInfo,
    val request_metadata: RequestMetadata,
    val request_parameters: RequestParameters
)