package com.example.poweractivity.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.poweractivity.R
import com.example.poweractivity.databinding.PriceItemLayoutBinding

class PriceItemAdapter(
    private var context: Context,
    private var list: ArrayList<String>,
    private var itemType: String,
) :
    RecyclerView.Adapter<PriceItemAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PriceItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.priceItemText.text = list[position]
        if (itemType == "NEW")
            holder.binding.priceItemLayout.setBackgroundColor(context.resources.getColor(R.color.light_blue))
        if (itemType == "USED")
            holder.binding.priceItemLayout.background = ContextCompat.getDrawable(context, R.drawable.gradient_bg)

        if (itemType == "FBA")
            holder.binding.priceItemLayout.setBackgroundColor(context.resources.getColor(R.color.light_green))

    }

    override fun getItemCount(): Int {
        return list.size

    }

    inner class ViewHolder(val binding: PriceItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {


    }
}

