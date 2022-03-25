package com.example.ScanPower.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchResult(
    val fulfillment: Fulfillment?,
    val inventory: Inventory?,
    val offers: Offers?,
    val position: Int?,
    val product: Product?
): Parcelable