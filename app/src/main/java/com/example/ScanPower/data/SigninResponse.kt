package com.example.ScanPower.data

data class SigninResponse(
    val accessToken: String,
    val email: String,
    val id: Int,
    val roles: List<String>,
    val tokenType: String,
    val username: String
)