package com.example.poweractivity.activities

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.poweractivity.R
import com.example.poweractivity.adapter.ViewPagerAdapter
import com.example.poweractivity.data.SearchResult
import com.example.poweractivity.data.Variant
import com.example.poweractivity.databinding.ActivityVariantBinding
import com.example.poweractivity.databinding.VariantLayoutBinding
import com.example.poweractivity.fragments.HomeFragment
import com.example.poweractivity.fragments.ItemDetailFragment
import com.example.poweractivity.fragments.ItemDetailFragment.Companion.newInstance
import com.example.poweractivity.fragments.VariantFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class VariantActivity : AppCompatActivity() {
    lateinit var binding: ActivityVariantBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVariantBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        /* intent.getParcelableExtra<Variant>("variant")?.let {
             replaceFragment(it?.let { it1 -> VariantFragment.newInstance(it1) })
         }*/
/*

        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        setUpViewPager(viewPager)
        TabLayoutMediator(tabLayout,viewPager)
        {
            tab,position->

                when(position)
                {
                  0->
                      tab.icon = ResourcesCompat.getDrawable(resources,R.drawable.newsfeed,null)

                }

        }






*/


    }


    fun replaceFragment(fragment: Fragment) {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        ft.replace(R.id.container, fragment)
        ft.commit()
    }

    /*  fun setUpViewPager(viewPager2: ViewPager2)
      {
          val adapter = ViewPagerAdapter(supportFragmentManager,lifecycle)
          adapter.addFragment(HomeFragment(),"")
          viewPager2.adapter = adapter

      }*/


}
