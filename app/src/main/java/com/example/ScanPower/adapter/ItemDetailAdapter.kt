package com.example.ScanPower.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ScanPower.Upc.Product
import com.example.ScanPower.databinding.ItemDetailLayoutBinding

class ItemDetailAdapter(
    val context: Context,
    val itemList: ArrayList<Product>,
) :
    RecyclerView.Adapter<ItemDetailAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemDetailLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val product = itemList[position].upc

        holder.binding.tvUpc.text = product
        Log.e("TAG", "Itemdetail adapter:$product ")


        /*holder.binding.root.setOnClickListener {
            context.startActivity(Intent(context, ItemDetailActivity::class.java)
                .putExtra("upc",itemList[position].upc))

        }*/


    }

    override fun getItemCount(): Int {
        return itemList.size

    }

    inner class ViewHolder(val binding: ItemDetailLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}