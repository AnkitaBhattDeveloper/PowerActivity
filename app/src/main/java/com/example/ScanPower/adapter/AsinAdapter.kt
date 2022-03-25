package com.example.ScanPower.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ScanPower.Upc.Specification
import com.example.ScanPower.databinding.ProductDetailLayoutBinding

class AsinAdapter(val context: Context, val asinList: ArrayList<Specification>) :
    RecyclerView.Adapter<AsinAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ProductDetailLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.tvkey.text = asinList[position].name
        holder.binding.tvValue.text = asinList[position].value
        Log.e("TAG",
            "onBindViewHolder: asinnnn adapter ${asinList[position].name} = ${asinList[position].value}")


        //Log.e("TAG", "onBindViewHolder: asin adapter ${asinList[position].} ")


    }

    override fun getItemCount(): Int {
        return asinList.size
    }

    inner class ViewHolder(val binding: ProductDetailLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }
}