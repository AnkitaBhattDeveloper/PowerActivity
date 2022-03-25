package com.example.ScanPower.data

data class RequestInfo(
    val credits_remaining: Int,
    val credits_reset_at: String,
    val credits_used: Int,
    val credits_used_this_request: Int,
    val success: Boolean
)