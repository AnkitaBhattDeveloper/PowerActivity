package com.example.syncritrage.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.poweractivity.databinding.CategoryItemListBinding
import com.example.poweractivity.databinding.RvTrendingItemsBinding

class TrendingAdapter(val context: Context,val trendItemList:ArrayList<String>): RecyclerView.Adapter<TrendingAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val binding = RvTrendingItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      holder.binding.tvProductName.text = trendItemList[position]
    }

    override fun getItemCount(): Int {
      return trendItemList.size
    }
     inner class ViewHolder(val binding:RvTrendingItemsBinding):
         RecyclerView.ViewHolder(binding.root) {

    }
}