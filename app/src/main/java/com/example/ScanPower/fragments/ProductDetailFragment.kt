package com.example.ScanPower.fragments

import Api.UpcNoService
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ScanPower.Repository.UpcRepository
import com.example.ScanPower.Upc.Specification
import com.example.ScanPower.Utils.App
import com.example.ScanPower.adapter.AsinAdapter
import com.example.ScanPower.data.SearchResult
import com.example.ScanPower.databinding.FragmentProductDetailBinding
import com.example.ScanPower.viewModel.SharedViewModel
import com.squareup.picasso.Picasso

class ProductDetailFragment : Fragment() {
    lateinit var binding: FragmentProductDetailBinding

    lateinit var conte: Context
    lateinit var product: SearchResult
    lateinit var viewModel: SharedViewModel

    lateinit var asinAdapter: AsinAdapter
    var alist: ArrayList<Specification> = arrayListOf()
    lateinit var aLayoutManager: LinearLayoutManager

    lateinit var repo: UpcRepository
    lateinit var upcNoService: UpcNoService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            product = (it.getParcelable<SearchResult>("product") as SearchResult)!!

        }

        /*  viewModel = (activity.run {
              ViewModelProvider(this@ProductDetailFragment).get(SharedViewModel::class.java)
          } ?: throw Exception("invalid Activity"))
  */

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.conte = context

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvProductName.text = product.product?.title
        Picasso.get().load(product.product?.main_image).into(binding.productImage)

        Log.e("TAG", "onViewCreated:  runnnnnnnnnnnnnnnnn")

        //repo = (activity?.application as App).upcRepository
        Log.e("TAG", "onViewCreated: ${App.UPC_NUMBER}")
        asinAdapter = AsinAdapter(conte, alist)
        aLayoutManager = LinearLayoutManager(requireContext())
        with(binding.rvProductDetail)
        {
            adapter = asinAdapter
            layoutManager = aLayoutManager
            isNestedScrollingEnabled = false
            hasFixedSize()

        }
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        viewModel.getItem().observe(requireActivity())
        {
            if (it != null) {
                alist.clear()
                alist.addAll(it.product.specifications)
                Log.e(
                    "TAG",
                    "VIEW MODEL :product detail fragment  ${it.product.specifications}",
                )
                asinAdapter.notifyDataSetChanged()

            }
            else{
                Toast.makeText(conte, "VIEW MODEL :product detail fragment  is nullllllll", Toast.LENGTH_SHORT).show()
            }


        }


    }

    companion object {
        @JvmStatic
        fun newInstance(data: SearchResult) =
            ProductDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("product", data)


                }
            }
    }


}