package com.example.ScanPower.activities

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.ScanPower.R
import com.example.ScanPower.Repository.UpcRepository
import com.example.ScanPower.Utils.App
import com.example.ScanPower.adapter.ViewPagerAdapter
import com.example.ScanPower.data.SearchResult
import com.example.ScanPower.databinding.ActivityItemDetailBinding
import com.example.ScanPower.fragments.BuyFragment
import com.example.ScanPower.fragments.GraphFragment
import com.example.ScanPower.fragments.ItemDetailFragment
import com.example.ScanPower.fragments.ProductDetailFragment
import com.example.ScanPower.viewModel.SharedViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ItemDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityItemDetailBinding
    lateinit var context: Context
    lateinit var itemDetailFragment: ItemDetailFragment
    lateinit var productDetailFragment: ProductDetailFragment
    lateinit var graphFragment: GraphFragment
    lateinit var buyFragment: BuyFragment
    lateinit var item: SearchResult
    lateinit var repo: UpcRepository
    lateinit var model: SharedViewModel
    lateinit var progressDailog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this
        val toolbar = binding.toolbar.toolbar
        setSupportActionBar(toolbar)


        progressDailog = ProgressDialog(context)
        progressDailog.setMessage("Hang on , Loading content")
        progressDailog.setTitle("Trust the process")
        progressDailog.setCancelable(false)
        progressDailog.show()


        repo = (application as App).upcRepository

       /* if (intent.hasExtra("keyword")) {
            buyFragment = BuyFragment.newInstance(intent.getParcelableExtra<SearchResult>("product")!!,
                intent.getStringExtra("keyword")!!)

        }*/
      /*  else{*/
            intent.getParcelableExtra<SearchResult>("product")?.let {
                itemDetailFragment = ItemDetailFragment.newInstance(it)
                productDetailFragment = ProductDetailFragment.newInstance(it)
                graphFragment = GraphFragment.newInstance(it)
                buyFragment = BuyFragment.newInstance(it,"")

                supportActionBar?.title = it.product?.title
                item = it
            }
        //}




        // repo.upcNumber(App.UPC_API_KEY, "product", App.ITEM_ID, App.USER_POSTAL_CODE)
        repo.upcNumber(App.UPC_API_KEY, "product", App.ITEM_ID, "62716")

        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        val tablayout = findViewById<TabLayout>(R.id.tabLayout)

        setupViewPager(viewPager)

        TabLayoutMediator(tablayout, viewPager)
        { tab, position ->
            when (position) {
                0 -> {
                    //tab.icon = ResourcesCompat.getDrawable(resources, R.drawable.logo_icon, null)
                    tab.setCustomView(R.layout.custom_navigation_item)
                }
                1 -> {
                    tab.icon = ResourcesCompat.getDrawable(resources, R.drawable.keys, null)
                }
                2 -> {
                    tab.icon =
                        ResourcesCompat.getDrawable(resources, R.drawable.newsfeed, null)
                }
                3 -> {
                    tab.icon = ResourcesCompat.getDrawable(resources, R.drawable.line, null)
                }
                /* 4 -> {
                     tab.icon = ResourcesCompat.getDrawable(resources, R.drawable.settings, null)
                 }*/

            }

        }.attach()
        model = ViewModelProvider(this).get(SharedViewModel::class.java)
        BlueCartViewModel()
       // AmazonViewModel()

    }

    fun setupViewPager(viewPager2: ViewPager2) {

        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        adapter.addFragment(itemDetailFragment, "cvvc")
        adapter.addFragment(productDetailFragment, "ccc")
        adapter.addFragment(buyFragment, "cvcb")
        adapter.addFragment(graphFragment, "vcv")
        //adapter.addFragment(SettingFragment(), "vcv")
        viewPager2.adapter = adapter
        //viewPager2.currentItem = 2

    }

    fun BlueCartViewModel() {

        repo.upcNumberResponse.observe(this)
        {
            if (it != null) {

                if (!it.product.upc.isNullOrEmpty()) {
                    binding.progressLayout.visibility = View.GONE
                    repo.asinNo(App.RAPID_API_HOST, App.RAPID_API_KEY, it.product.upc, "US")
                    model.setItem(it)
                    progressDailog.dismiss()
                }


            }


        }

    }

    /*fun AmazonViewModel() {
        repo.asinResponse.observe(this)
        {
            if (it != null) {
                binding.progressLayout.visibility = View.GONE
                model.setRapid(it)
            }


        }

    }*/
}