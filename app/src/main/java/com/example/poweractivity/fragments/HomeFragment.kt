package com.example.poweractivity.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import com.example.poweractivity.R
import com.example.poweractivity.databinding.FragmentHomeBinding
import java.util.jar.Manifest
import androidx.core.app.ActivityCompat.requestPermissions

import android.content.pm.PackageManager
import android.location.Geocoder
import android.util.Log
import java.util.*


class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding

    val REQUEST_CODE = 111
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (ActivityCompat.checkSelfPermission(requireContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            //&&
//            ActivityCompat.checkSelfPermission(requireContext(),
//                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
            activity?.let {

                requestPermissions(it,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION),REQUEST_CODE)

                Log.e("TAG", "onViewCreated: ********" )



            }


        } else {
            Log.e("tag", "permission checked")
        }

        binding.btn.setOnClickListener()
        {
            val city = binding.edit.text.toString()
            val gc = Geocoder(context, Locale.getDefault())
            val addresses = gc.getFromLocationName(city,3)
            Log.e("tag", "onViewCreated: ${addresses}" )
            val address = addresses.get(0)
            Log.e("tag", "onViewCreated: ${address}" )
            binding.text.setText(
                    " ${address.countryCode},\n" +
                    " ${address.countryName},\n" +
                    " ${address.postalCode},\n" +
                    " ${address.adminArea}")



        }


    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE && grantResults[0]==PackageManager.PERMISSION_GRANTED)
        {
            Log.e("TAG", "onRequestPermissionsResult: ")
        }
    }

    companion object {
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}