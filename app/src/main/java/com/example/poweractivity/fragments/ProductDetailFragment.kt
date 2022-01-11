package com.example.poweractivity.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.poweractivity.R
import com.example.poweractivity.databinding.FragmentBuyBinding
import com.example.poweractivity.databinding.FragmentProductDetailBinding

class ProductDetailFragment : Fragment() {
lateinit var binding:FragmentProductDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View?
    {
        binding = FragmentProductDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProductDetailFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}