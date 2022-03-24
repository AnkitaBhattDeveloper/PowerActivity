package com.example.poweractivity.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Next(
    val link: String,
    val page: Int
):Parcelable