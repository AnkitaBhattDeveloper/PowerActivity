package com.example.ScanPower.fragments

import Api.UpcNoService
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ScanPower.R
import com.example.ScanPower.Repository.UpcRepository
import com.example.ScanPower.Utils.App
import com.example.ScanPower.activities.CategoryClickListener
import com.example.ScanPower.adapter.VariantAdapter
import com.example.ScanPower.data.SearchResult
import com.example.ScanPower.data.Variant
import com.example.ScanPower.databinding.FragmentItemDetailBinding
import com.example.ScanPower.viewModel.SharedViewModel
import com.squareup.picasso.Picasso

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ItemDetailFragment : Fragment(), CategoryClickListener {
    lateinit var binding: FragmentItemDetailBinding

    lateinit var itemlayoutManager: LinearLayoutManager
    lateinit var con: Context
    lateinit var product: SearchResult
    lateinit var repo: UpcRepository
    lateinit var item_id: String
    lateinit var upcNoService: UpcNoService
    lateinit var model: SharedViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            product = (it.getParcelable<SearchResult>("product") as SearchResult)!!


        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.con = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentItemDetailBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.tvItemText.text = product.product?.title
        binding.tvItemBrand.text = product.product?.brand
        val symbol = product.offers?.primary?.currency_symbol
        val price = product.offers?.primary?.price
        binding.tvItemPrice.text = "${symbol} ${price}"
        product.product?.description = product.product?.description?.replace("<li>", " ")
        product.product?.description = product.product?.description?.replace("</li>", " ")
        product.product?.description = product.product?.description?.replace("*", " ")

        binding.tvItemDescription.text = product.product?.description
        Picasso.get().load(product.product?.main_image).into(binding.ivItemImage)


        Log.e("TAG", "onViewCreated: product id ${product.product?.product_id}")
        Log.e("TAG", "onViewCreated: item id ${product.product?.item_id}")


        val itemadapter = product.product?.variants.let {
            it?.let { it1 -> VariantAdapter(requireContext(), it1, this) }
        }
        itemlayoutManager = LinearLayoutManager(con, LinearLayoutManager.HORIZONTAL, false)
        with(binding.rvMoreItems)
        {
            adapter = itemadapter
            layoutManager = itemlayoutManager

            Log.e("TAG", "onViewCreated variantsssssssssss: ${product.product?.variants?.size}")
            if (product.product?.variants.isNullOrEmpty()) {
                binding.variantsLayout.visibility = View.GONE

            }
        }
        binding.ivFavourite.setOnClickListener {
            context?.resources?.let { it1 -> binding.ivFavourite.setColorFilter(it1.getColor(R.color.pink)) }

        }

        repo = (activity?.application as App).upcRepository
        Log.e("TAG", "onViewCreated: asin upc numberrrrrrrr   ${App.UPC_NUMBER}")


        model = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        model.getItem().observe(requireActivity())
        {
            if (it != null) {
                Log.e("TAG", "onViewCreated: fromm shared view model ${it.product}")
            } else {
                Log.e(
                    "TAG",
                    "onViewCreated: else condition in itemdetail fragment of shared view model",
                )
            }
        }


    }

    companion object {
        @JvmStatic
        fun newInstance(data: SearchResult) =
            ItemDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("product", data)
                }
            }

    }

    override fun onClickListener(itemListener: Variant) {
        Picasso.get().load(itemListener.image).into(binding.ivItemImage)
        binding.tvItemBrand.text = itemListener.title
    }


    override fun getCategoryNameListener(storeId: String) {
        TODO("Not yet implemented")
    }

    /*override fun getCategoryNameListener(categoryName: String) {
        Log.e("TAG", "getCategoryNameListener: $categoryName", )
    }*/


}