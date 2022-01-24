package com.example.syncritrage.fragments

import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.poweractivity.databinding.FragmentHomeBinding
import com.example.syncritrage.adapter.CategoryAdapter
import com.example.syncritrage.adapter.SliderAdapter
import com.example.syncritrage.adapter.TrendingAdapter
import com.example.syncritrage.data.SliderItem
import java.util.*


class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    var catName: ArrayList<String> = arrayListOf()
    var trendPName: ArrayList<String> = arrayListOf()


    // private var s: String? = "null"
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
            &&
            ActivityCompat.checkSelfPermission(requireContext(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
            activity?.let {
                requestPermissions(it,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION), REQUEST_CODE)
            }

        } else {
            Log.e("tag", "permission checked")
        }

        binding.btn.setOnClickListener()
        {
            val city = binding.etPin.text.toString()
            val gc = Geocoder(context, Locale.getDefault())
            val addresses = gc.getFromLocationName(city, 3)
            Log.e("tag", "onViewCreated: ${addresses}")
            val address = addresses.get(0)
            Log.e("tag", "onViewCreated: ${address}")
            binding.etPin.setText(" ${address.postalCode}," + " " + " ${address.subAdminArea}")

        }

        val categoryAdapter = CategoryAdapter(requireContext(), catName)
        val lManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvCategory.apply {
            adapter = categoryAdapter
            layoutManager = lManager
            hasFixedSize()
        }

        catName.add("Mobiles")
        catName.add("Fashion")
        catName.add("Grocery")
        catName.add("Electronics")
        catName.add("Furniture")
        catName.add("Home")
        catName.add("Appliances")


        // val sliderView: SliderView = findViewById(R.id.imageSlider)
        val sliderAdapter = SliderAdapter(requireContext())
        sliderAdapter.addItem(SliderItem("", ""))
        sliderAdapter.addItem(SliderItem("", ""))
        sliderAdapter.addItem(SliderItem("", ""))

        binding.imageSlider.setSliderAdapter(sliderAdapter)

        val trendingAdapter = TrendingAdapter(requireContext(), trendPName)
        val tLManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvTrending.apply {
            adapter = trendingAdapter
            layoutManager = tLManager
            hasFixedSize()
        }
        trendPName.add("jdfhfhh")
        trendPName.add("jdfhfhh")
        trendPName.add("jdfhfhh")
        trendPName.add("jdfhfhh")
        trendPName.add("jdfhfhh")
        trendPName.add("jdfhfhh")
        trendPName.add("jdfhfhh")
        trendPName.add("jdfhfhh")


    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
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