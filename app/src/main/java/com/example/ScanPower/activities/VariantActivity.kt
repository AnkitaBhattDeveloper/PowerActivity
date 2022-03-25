package com.example.ScanPower.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.ScanPower.R
import com.example.ScanPower.databinding.ActivityVariantBinding

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
