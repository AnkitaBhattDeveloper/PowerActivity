package com.example.ScanPower.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Fulfillment(
    val delivery_from_store: Boolean,
    val pickup: Boolean,
    val shipping: Boolean,
    val shipping_days: Int
): Parcelable