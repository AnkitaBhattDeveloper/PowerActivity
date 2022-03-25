package com.example.ScanPower.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Current(
    val link: String,
    val page: Int,
) : Parcelable