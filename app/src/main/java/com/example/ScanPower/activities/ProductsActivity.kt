package com.example.ScanPower.activities

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ScanPower.R
import com.example.ScanPower.Repository.BlueCartRepository
import com.example.ScanPower.Repository.UpcRepository
import com.example.ScanPower.Utils.App
import com.example.ScanPower.adapter.viewPagerAdapter
import com.example.ScanPower.databinding.ActivityProductsBinding
import com.example.ScanPower.fragments.HomeFragment
import com.example.ScanPower.fragments.SubProductsFragment
import com.example.ScanPower.viewModel.SharedViewModel

class ProductsActivity : AppCompatActivity() {
    lateinit var binding: ActivityProductsBinding
    lateinit var context: Context
    lateinit var adapter: viewPagerAdapter
    lateinit var view: View
    lateinit var viewModel: SharedViewModel
    lateinit var homeFragment: HomeFragment
    lateinit var repo: UpcRepository
    lateinit var repoStore: BlueCartRepository
    lateinit var keyword: String
    lateinit var storeId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this
        Log.e("TAG", "onCreate:------------------ ")
        val toolbar = binding.includeToolbar.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.title = intent.getStringExtra("name")

        repo = (application as App).upcRepository
        if (intent.hasExtra("storeId")) {
            replacefragment(SubProductsFragment.newInstance(intent.getStringExtra("name")
                .toString(), intent.getStringExtra("storeId").toString()))

        } else {
            replacefragment(SubProductsFragment.newInstance(intent.getStringExtra("name")
                .toString(), ""))
        }
        keyword = intent.getStringExtra("name").toString()
        storeId = intent.getStringExtra("storeId").toString()

        viewModel = ViewModelProvider(this).get(SharedViewModel::class.java)
        //amazonPrice()


    }


    fun replacefragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val ft = fragmentManager.beginTransaction()
        ft.replace(R.id.container, fragment)
        ft.commit()
    }

    /*  private fun amazonPrice() {
          repo.asinNo(App.RAPID_API_HOST,App.RAPID_API_KEY,keyword,"US")
          repo.asinResponse.observe(this) {
              viewModel.setRapid(it)
          }

      }*/

}