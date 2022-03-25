package com.example.ScanPower.data

data class StoreIDResponse(
    val location_info: LocationInfoX,
    val pagination: PaginationX,
    val request_info: RequestInfoX,
    val request_metadata: RequestMetadataX,
    val request_parameters: RequestParametersX,
    val search_results: List<SearchResultX>
)