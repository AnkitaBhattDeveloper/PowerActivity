package com.example.syncritrage.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.poweractivity.R
import com.example.poweractivity.databinding.CategoryItemListBinding

import com.example.syncritrage.activities.ViewCategoryActivity


class CategoryAdapter(val context:Context,var categoryList:ArrayList<String>) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
val binding = CategoryItemListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvCat.text = categoryList[position]
        if (position==0){
            holder.binding.ivCategory.setImageResource(R.drawable.mobile)
        }
        if (position==1){
            holder.binding.ivCategory.setImageResource(R.drawable.fasgion)
        }
        if (position==2){
            holder.binding.ivCategory.setImageResource(R.drawable.grocery)
        }
        if (position==3){
            holder.binding.ivCategory.setImageResource(R.drawable.appliances)
        }
        if (position==4){
            holder.binding.ivCategory.setImageResource(R.drawable.furniture)
        }
        if (position==5){
            holder.binding.ivCategory.setImageResource(R.drawable.home)
        }
        if (position==6){
            holder.binding.ivCategory.setImageResource(R.drawable.electronic_items)
        }

        holder.binding.root.setOnClickListener() {
            context.startActivity(Intent(context, ViewCategoryActivity::class.java)
                .putExtra("name",categoryList[position]))
        }

    }

    override fun getItemCount(): Int {
      return categoryList.size
    }

    inner class ViewHolder(val binding: CategoryItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {


    }
}
