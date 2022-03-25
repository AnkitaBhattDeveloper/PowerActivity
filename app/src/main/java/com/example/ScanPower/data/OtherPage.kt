package com.example.ScanPower.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OtherPage(
    val link: String,
    val page: Int
):Parcelable