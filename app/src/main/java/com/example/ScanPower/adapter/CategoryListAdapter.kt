package com.example.ScanPower.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ScanPower.activities.CategoryClickListener
import com.example.ScanPower.activities.ProductsActivity
import com.example.ScanPower.data.CategoryListModel
import com.example.ScanPower.databinding.CategoryListLayoutBinding

class CategoryListAdapter(
    val context: Context,
    val categoryList: ArrayList<CategoryListModel>,
    val listener: CategoryClickListener,
    val type: String,
) :
    RecyclerView.Adapter<CategoryListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            CategoryListLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvCategory.text = categoryList[position].categoryName
        holder.binding.ivCategory.setImageDrawable(categoryList[position].categoryImage)

        if (type == "home") {
            holder.binding.root.setOnClickListener()
            {
                context.startActivity(Intent(context, ProductsActivity::class.java)
                    .putExtra("name", categoryList[position].categoryName))

            }
        }
        if (type == "bottom") {
            //listener.getCategoryNameListener(categoryList[position].toString())

            holder.binding.root.setOnClickListener()
            {
                listener.getCategoryNameListener(categoryList[position].categoryName)
            }
        }

    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    inner class ViewHolder(val binding: CategoryListLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

}