package com.example.poweractivity.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    val best_seller: Boolean?,
    val brand: String?,
    var description: String?,
    val images: List<String>?,
    val item_id: String?,
    val link: String?,
    val main_image: String?,
    val product_id: String?,
    val rating: Double?,
    val ratings_total: Int?,
    val title: String?,
    val variants: List<Variant>?
): Parcelable