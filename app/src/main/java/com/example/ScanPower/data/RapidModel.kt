package com.example.ScanPower.data

import com.google.gson.annotations.SerializedName


data class RapidModel(
    @SerializedName("ASIN")
    val ASIN: String?,
    @SerializedName("detailPageURL")
    val detailPageURL: String?,
    @SerializedName("imageUrl")
    val imageUrl: String?,
    @SerializedName("isPrimeEligible")
    val isPrimeEligible: String?,
    @SerializedName("listPrice")
    val listPrice: String?,
    @SerializedName("price")
    val price: String?,
    @SerializedName("rating")
    val rating: String?,
    @SerializedName("subtitle")
    val subtitle: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("totalReviews")
    val totalReviews: String?
)
