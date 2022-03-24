package com.example.poweractivity.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.poweractivity.data.SubCategoryModel
import com.example.poweractivity.databinding.StoreProductLayoutBinding


class SubCategoryAapter(val context: Context, val subCategoryList: ArrayList<SubCategoryModel>) :
    RecyclerView.Adapter<SubCategoryAapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            StoreProductLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //holder.binding.tvSubCategory.text = subCategoryList[position].subCategoryName


    }

    override fun getItemCount(): Int {
        return subCategoryList.size
    }

    inner class ViewHolder(val binding: StoreProductLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}