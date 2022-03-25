package com.example.ScanPower.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.ScanPower.data.RapidModel
import com.example.ScanPower.databinding.PriceItemLayoutBinding
import com.squareup.picasso.Picasso

class PriceItemAdapter(
    private var context: Context,
    private var list: ArrayList<RapidModel>,
) :
    RecyclerView.Adapter<PriceItemAdapter.ViewHolder>() {

    private lateinit var uri: Uri
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            PriceItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if(list != null) {
            val rating = list[position]?.rating
            val image = list[position]?.imageUrl
            val url = list[position]?.detailPageURL
            holder.binding.priceItemText.text = list[position]?.price
            holder.binding.priceItemText.text = list[position]?.title
//        holder.binding.ratings.rating = rating?.toFloat()!!
            Picasso.get().load(image).into(holder.binding.ivImage)



            if (url != null) {
                holder.binding.root.setOnClickListener {
                    uri = Uri.parse(url)
                    context.startActivity(Intent(Intent.ACTION_VIEW, uri))
                    Log.e("TAG", "onBindViewHolder: amazon link click $uri")

                }
            } else {
                Toast.makeText(context, "no offers found", Toast.LENGTH_SHORT).show()
            }
        }


        else
        {
            Log.e("TAG", "onBindViewHolder:list is null $list")
            Toast.makeText(context, "list is null", Toast.LENGTH_SHORT).show()
        }


    }

    override fun getItemCount(): Int {
        return list.size

    }

    inner class ViewHolder(val binding: PriceItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}

