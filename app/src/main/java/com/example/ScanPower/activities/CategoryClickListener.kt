package com.example.ScanPower.activities

import com.example.ScanPower.data.Variant

interface CategoryClickListener {
    fun onClickListener(itemListener: Variant)
    fun getCategoryNameListener(storeCategoryName: String)
}