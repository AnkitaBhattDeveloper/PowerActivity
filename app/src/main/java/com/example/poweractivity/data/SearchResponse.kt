package com.example.poweractivity.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchResponse(
    val location_info: LocationInfo?,
    val pagination: Pagination,
  /*  val request_info: RequestInfo,
    val request_metadata: RequestMetadata,
    val request_parameters: RequestParameters,*/
    val search_results: ArrayList<SearchResult?>,
):Parcelable