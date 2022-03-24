package com.example.poweractivity.activities

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.poweractivity.R
import com.example.poweractivity.Repository.UpcRepository
import com.example.poweractivity.Utils.App
import com.example.poweractivity.databinding.ActivityMainBinding
import com.example.poweractivity.databinding.BottomSheetLayoutBinding
import com.example.poweractivity.fragments.BuyFragment
import com.example.poweractivity.fragments.HomeFragment
import com.example.poweractivity.viewModel.SharedViewModel
import com.google.android.gms.location.*
import com.google.android.material.bottomsheet.BottomSheetDialog


class  MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var context: Context
    lateinit var binding1: BottomSheetLayoutBinding


    //private lateinit var bottomNavigationView: BottomNavigationView
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var locationRequest: LocationRequest
    lateinit var locationCallback: LocationCallback
    lateinit var location: Location
    lateinit var geocoder: Geocoder
    lateinit var model: SharedViewModel


    val requestCode = 101

    //var text1 = ""
    var addressess: List<Address> = listOf()
    lateinit var locationResult: LocationResult
    lateinit var locationManager: LocationManager
    lateinit var upcRepository: UpcRepository
    var searchname = ""

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this
        supportActionBar!!.hide()
        replaceFragment(HomeFragment.newInstance("", ""))
        isPermissionChecked()


//        fetchLocation()

        upcRepository = (application as App).upcRepository
        model = ViewModelProvider(this).get(SharedViewModel::class.java)

        binding.ivSearch.setOnClickListener {
            searchname = binding.etSearchAsin.text.toString()
            if (searchname.isNullOrEmpty()) {
                Log.e("TAG", "onCreate: you can not search product")
                binding.etSearchAsin.error = "Please type something"
            } else {
                Log.e("TAG", "onCreate: you can  search product ${searchname}")
                //searchSheet()
                replaceFragment(BuyFragment.newInstance(null, searchname))

                /*  startActivity(Intent(context,ItemDetailActivity::class.java)
                      .putExtra("keyword",searchname))
                  Log.e("TAG", "onCreate: you can  search product ${binding.etSearchAsin.text}", )*/
            }

        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if ((requestCode == 101 && grantResults.size > 0) && grantResults[0] + grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            fetchLocation()
        } else {
            Toast.makeText(context, "permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    private fun replaceFragment(f: Fragment) {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        ft.replace(R.id.mainFrame, f)
        ft.commit()
    }

    private fun isPermissionChecked() {

        if (ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(context as Activity,
                arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION), 101)

        } else {
            //fetchLocation()
            binding.tvpincode.visibility = View.VISIBLE
        }

    }

    fun fetchLocation() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER)
        ) {
            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }

            fusedLocationProviderClient.lastLocation.addOnCompleteListener {
                it.result.let {
                    location = it

                }
                if (location == null) {
                    locationRequest = LocationRequest().setInterval(10000)
                        .setFastestInterval(1000)
                        .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                        .setNumUpdates(1)
                    locationCallback.apply {
                        location = locationResult.lastLocation
                        binding.tvpincode.text = (location.latitude).toString()
                        Log.e("TAG",
                            "fetchLocation latitude :::::: ${location.latitude} \n longitude :::::::: ${location.longitude} ${location} ")
                    }

                } else {
                    binding.tvpincode.text = (location.latitude).toString()
                    Log.e("TAG",
                        "fetchLocation on location not null latitude :::::: ${location.latitude} \n longitude :::::::: ${location.longitude}")
                    val latitude = location.latitude
                    val longitude = location.longitude
                    geocoder = Geocoder(context)
                    addressess = geocoder.getFromLocation(latitude, longitude, 1)
                    val address: Address = addressess.get(0)

                    App.setString(context, App.USER_POSTAL_CODE, address.postalCode)

                    Log.e("TAG", "fetchLocation postal code: ${address.postalCode}")
                    binding.tvpincode.text =
                        "Deliver to ${address.subAdminArea} ${address.postalCode} "


                }
            }
        } else {
            startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK))

        }
    }

    private fun searchSheet() {
        val sheet = BottomSheetDialog(context)
        binding1 = BottomSheetLayoutBinding.inflate(layoutInflater)
        sheet.setContentView(binding1.root)

    }


}




