package com.example.ScanPower.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Pagination(
    val current: Current,
    val next: Next,
    val other_pages: List<OtherPage>,
    val total_pages: Int,
    val total_results: Int
):Parcelable