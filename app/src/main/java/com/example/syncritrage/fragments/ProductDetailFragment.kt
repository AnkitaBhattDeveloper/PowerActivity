package com.example.syncritrage.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.poweractivity.R
import com.example.poweractivity.databinding.FragmentProductDetailBinding

class ProductDetailFragment : Fragment() {
    lateinit var binding: FragmentProductDetailBinding
    lateinit var b30Days: AppCompatButton
    lateinit var b180Days: AppCompatButton
    lateinit var b365Days: AppCompatButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
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
        Log.e("TAG", "onViewCreated:  runnnnnnnnnnnnnnnnn" )
        b30Days = binding.btn30Day
        b180Days = binding.btn180Day
        b365Days = binding.btn365Day


        b30Days.setOnClickListener {
            b30Days.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.gradient_bg)
            b180Days.setBackgroundColor(ContextCompat.getColor(requireContext(),
                R.color.d_grey))
            b365Days.setBackgroundColor(ContextCompat.getColor(requireContext(),
                R.color.d_grey))
        }


        b180Days.setOnClickListener {
            b180Days.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.gradient_bg)
            b30Days.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.d_grey))
            b365Days.setBackgroundColor(ContextCompat.getColor(requireContext(),
                R.color.d_grey))
        }

        b365Days.setOnClickListener {
            b365Days.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.gradient_bg)
            b30Days.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.d_grey))
            b180Days.setBackgroundColor(ContextCompat.getColor(requireContext(),
                R.color.d_grey))
        }

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