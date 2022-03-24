package com.example.poweractivity.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Inventory(
    val in_stock: Boolean,
    val preorder: Boolean
): Parcelable