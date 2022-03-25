package com.example.ScanPower.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ScanPower.R
import com.example.ScanPower.activities.CategoryClickListener
import com.example.ScanPower.activities.ProductsActivity
import com.example.ScanPower.adapter.CategoryListAdapter
import com.example.ScanPower.data.CategoryListModel
import com.example.ScanPower.data.Variant
import com.example.ScanPower.databinding.BottomSheetLayoutBinding
import com.example.ScanPower.databinding.FragmentHomeBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.material.bottomsheet.BottomSheetDialog


class HomeFragment : Fragment(), CategoryClickListener {
    lateinit var binding: FragmentHomeBinding
    lateinit var con: Context
    val categoryName: ArrayList<CategoryListModel> = arrayListOf()
    lateinit var binding1: BottomSheetLayoutBinding


    //location
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var locationRequest: LocationRequest
    lateinit var locationCallback: LocationCallback
    var currentLocation: Location? = null
    var locationByGps: Location? = null

    lateinit var locationManager: LocationManager


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




        return binding.root


    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.con = context
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val categoryListAdapter = CategoryListAdapter(requireContext(), categoryName, this, "home")
        val categoryGridLayoutManager = GridLayoutManager(context, 3)
        with(binding.rvCategory)
        {
            adapter = categoryListAdapter
            layoutManager = categoryGridLayoutManager
            addCategoryToList()

        }

        binding.fab.setOnClickListener {
            this.BottomSheet()

        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

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
        // outState.putString("val", binding.etPin.text.toString())
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    fun addCategoryToList() {
        categoryName += CategoryListModel("Mobiles",
            context?.getDrawable(R.drawable.smartphone)!!)
        categoryName += CategoryListModel("Fashion",
            context?.getDrawable(R.drawable.sweatshirt)!!)
        categoryName += CategoryListModel("Electronics",
            context?.getDrawable(R.drawable.electronic_device)!!)
        categoryName += CategoryListModel("Grocery", context?.getDrawable(R.drawable.food)!!)
        categoryName += CategoryListModel("pets", context?.getDrawable(R.drawable.dog)!!)
        categoryName += CategoryListModel("home", context?.getDrawable(R.drawable.decor)!!)
        categoryName += CategoryListModel("furniture", context?.getDrawable(R.drawable.sofa)!!)
        categoryName += CategoryListModel("beauty", context?.getDrawable(R.drawable.cosmetics)!!)
        categoryName += CategoryListModel("personal care",
            context?.getDrawable(R.drawable.products)!!)
        categoryName += CategoryListModel("appliances",
            context?.getDrawable(R.drawable.appliances)!!)
        categoryName += CategoryListModel("patio & garden",
            context?.getDrawable(R.drawable.gardening)!!)
        categoryName += CategoryListModel("camping gear",
            context?.getDrawable(R.drawable.tool)!!)
        categoryName += CategoryListModel("auto", context?.getDrawable(R.drawable.automobile)!!)
        categoryName += CategoryListModel("computers",
            context?.getDrawable(R.drawable.desktop)!!)
        categoryName += CategoryListModel("arts and crafts",
            context?.getDrawable(R.drawable.paper_crafts)!!)
        categoryName += CategoryListModel("books", context?.getDrawable(R.drawable.book_stack)!!)
        categoryName += CategoryListModel("tv & movies",
            context?.getDrawable(R.drawable.clapperboard)!!)
        categoryName += CategoryListModel("toys", context?.getDrawable(R.drawable.toys)!!)
        categoryName += CategoryListModel("sports", context?.getDrawable(R.drawable.sports)!!)

    }

    fun BottomSheet() {
        val bottomSheetDialog = BottomSheetDialog(con)
        binding1 = BottomSheetLayoutBinding.inflate(layoutInflater)
        bottomSheetDialog.setContentView(binding1.root)
        val categoryStoreAdapter =
            CategoryListAdapter(requireContext(), categoryName, this, "bottom")

        with(binding1.rvStoreCategory)
        {

            adapter = categoryStoreAdapter
            val categoryGridLayoutManager = GridLayoutManager(context, 2)
            layoutManager = categoryGridLayoutManager
            addCategoryToList()
        }

        bottomSheetDialog.show()


    }

    override fun onClickListener(itemListener: Variant) {
        Log.e("TAG", "onClickListener: ")
    }

    override fun getCategoryNameListener(storeCategoryName: String) {
        if (binding1.storeId.text.trim().isNullOrEmpty()) {
            binding1.storeId.setError("enter store id")
            binding1.storeId.requestFocus()

        } else {
            startActivity(Intent(context, ProductsActivity::class.java)
                .putExtra("storeId", binding1.storeId.text.toString())
                .putExtra("name", storeCategoryName))
        }
    }


}
