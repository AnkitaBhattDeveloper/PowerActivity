package com.example.poweractivity.activities

import com.example.poweractivity.data.CategoryListModel
import com.example.poweractivity.data.Variant

interface CategoryClickListener {
    fun onClickListener(itemListener: Variant)
    fun getCategoryNameListener(storeCategoryName: String)
}