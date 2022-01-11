package com.example.poweractivity.activities

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.widget.ViewPager2
import com.example.poweractivity.R
import com.example.poweractivity.adapter.ViewPagerAdapter
import com.example.poweractivity.databinding.ActivityMainBinding
import com.example.poweractivity.fragments.*
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var context: Context
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this
        supportActionBar!!.hide()
        replaceFragment(HomeFragment.newInstance("", ""))
        binding.bottomNavigationView.menu.getItem(2).isChecked = true
        binding.bottomNavigationView.setOnNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.menuDetails -> {
                    replaceFragment(ProductDetailFragment.newInstance("", ""))
                }
                R.id.menuBuy -> {
                    replaceFragment(BuyFragment.newInstance("", ""))
                }
                R.id.menuHome -> {
                    replaceFragment(HomeFragment.newInstance("", ""))

                }
                R.id.menuGraph -> {
                    replaceFragment(GraphFragment.newInstance("", ""))

                }
                R.id.menuSettings -> {
                    replaceFragment(SettingFragment.newInstance("", ""))

                }
            }
            true
        }


    }



    private fun replaceFragment(f: Fragment) {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        ft.replace(R.id.mainFrame, f)
        ft.commit()
    }


}


