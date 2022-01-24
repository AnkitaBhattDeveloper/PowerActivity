package com.example.syncritrage.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.poweractivity.R


class ViewCategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_category)
        supportActionBar?.hide()
    }
}