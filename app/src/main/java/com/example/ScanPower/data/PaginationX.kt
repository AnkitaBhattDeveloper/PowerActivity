package com.example.ScanPower.data

data class PaginationX(
    val current: CurrentX,
    val next: NextX,
    val other_pages: List<OtherPageX>,
    val total_pages: Int,
    val total_results: Int
)