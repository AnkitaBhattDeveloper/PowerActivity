package com.example.ScanPower.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Offers(
    val is_marketplace_item: Boolean,
    val primary: Primary
): Parcelable