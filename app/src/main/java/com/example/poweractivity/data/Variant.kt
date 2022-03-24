package com.example.poweractivity.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Variant(
    val image: String,
    val item_id: String,
    val link: String,
    val product_id: String,
    val title: String
): Parcelable

