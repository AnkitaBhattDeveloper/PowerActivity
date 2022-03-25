package com.example.ScanPower.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LocationInfo(
    val city: String,
    val state: String,
    val store_id: String,
    val zipcode: String
):Parcelable