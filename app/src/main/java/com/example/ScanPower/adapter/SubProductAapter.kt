package com.example.ScanPower.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ScanPower.Utils.App
import com.example.ScanPower.activities.ItemDetailActivity
import com.example.ScanPower.data.SearchResult
import com.example.ScanPower.databinding.SubProductListLayoutBinding
import com.squareup.picasso.Picasso

class SubProductAapter(
    val context: Context,
    val subProductList: ArrayList<SearchResult?>,

    ) :
    RecyclerView.Adapter<SubProductAapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            SubProductListLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        val product = subProductList[position]?.product
        val price = subProductList[position]?.offers?.primary
       // holder.binding.tvSubProductName.text = product?.title
        holder.binding.tvSubProductPrice.text = price?.currency_symbol + " " + price?.price
        if(product?.title?.length!! <= 40)
        {
            holder.binding.tvSubProductName.text = product?.title
        }
        else
        {
            holder.binding.tvSubProductName.text = product?.title.substring(0,40)
        }




        Picasso.get().load(subProductList[position]?.product?.main_image)
            .into(holder.binding.ivSubProductImage)

        holder.binding.root.setOnClickListener {
            context.startActivity(Intent(context, ItemDetailActivity::class.java)
                .putExtra("product", subProductList[position]))

            Log.e("TAG", "click on item detail activity: errorrrrrrrr")
            App.ITEM_ID = product?.item_id.toString()
        }


    }

    override fun getItemCount(): Int {
        Log.e("TAG", "getItemCount: errorrrrrrrr")
        return subProductList.size

    }

    inner class ViewHolder(val binding: SubProductListLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}