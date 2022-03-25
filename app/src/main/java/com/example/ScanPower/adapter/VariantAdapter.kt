package com.example.ScanPower.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ScanPower.activities.CategoryClickListener
import com.example.ScanPower.data.Variant
import com.example.ScanPower.databinding.VariantLayoutBinding
import com.squareup.picasso.Picasso

class VariantAdapter(
    val context: Context,
    private val variantList: List<Variant>,
    private val listener: CategoryClickListener,
) : RecyclerView.Adapter<VariantAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            VariantLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.variantText.text = variantList[position].title
        Picasso.get().load(variantList[position].image).into(holder.binding.variantImage)
        //holder.binding.variantPrice.text = variantList[position].
        holder.binding.root.setOnClickListener {
            //  context.startActivity(Intent(context, VariantActivity::class.java))
            listener.onClickListener(variantList[position])
        }
//        listener.onClickListener(variantList[position])
    }

    override fun getItemCount(): Int {
        return variantList.size
    }

    inner class ViewHolder(val binding: VariantLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)


}