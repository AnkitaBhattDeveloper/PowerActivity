package com.example.poweractivity.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


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
