package com.example.poweractivity.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Seller(
    val id: String,
    val name: String
): Parcelable