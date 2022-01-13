package com.example.poweractivity.fragments

import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.fragment.app.Fragment
import com.example.poweractivity.R
import com.example.poweractivity.databinding.FragmentHomeBinding
import java.util.*


class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding

    private var s: String? = "null"
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


//        binding.etPin.text = s
        if (savedInstanceState != null) {
            Log.e("TAG", "onCreateView: " + savedInstanceState.get("val"))
            s = savedInstanceState.get("val").toString()
            Toast.makeText(context, s, Toast.LENGTH_LONG).show()
            //   binding.etPin.setText("ffffff"+s)
            Log.e("TAG", "onCreateView: $s")
            binding.etPin.setText(s)
        }
//        if (s != null)
//            binding.etPin.text = s

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

                Log.e("TAG", "onViewCreated: ********")

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
            binding.etPin.setText(
                //                    " ${address.countryCode},\n" +
                //                    " ${address.countryName},\n" +
                " ${address.postalCode}," +
                        //                    " ${address.adminArea},\n"+
                        " ${address.subAdminArea}")


        }


        val animation: Animation =
            AnimationUtils.loadAnimation(requireContext(), R.anim.aaaa)
        binding.ivAnime.startAnimation(animation)


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        if (savedInstanceState != null) {
            Log.e("TAG", "onCreateView: " + savedInstanceState.get("val"))
            binding.etPin.setText("dsfsgfhcdghg")

            s = savedInstanceState.get("val").toString()
            Toast.makeText(context, s, Toast.LENGTH_LONG).show()
            //   binding.etPin.setText("ffffff"+s)
            Log.e("TAG", "onCreateView: $s")

        }


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


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("val", binding.etPin.text.toString())
    }
}