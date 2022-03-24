package com.example.poweractivity.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Primary(
    val currency_symbol: String,
    val id: String,
    val list_price: Double,
    val price: Double,
    val savings_amount: Double,
    val seller: Seller
): Parcelable