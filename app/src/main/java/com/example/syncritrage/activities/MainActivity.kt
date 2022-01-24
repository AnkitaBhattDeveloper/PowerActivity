package com.example.syncritrage.activities

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.poweractivity.R
import com.example.poweractivity.databinding.ActivityMainBinding
import com.example.syncritrage.fragments.*
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var context: Context
    private lateinit var bottomNavigationView: BottomNavigationView

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this
        supportActionBar!!.hide()
        replaceFragment(HomeFragment.newInstance("", ""))

        //binding.bottomNavigationView.menu.getItem(2).isEnabled = false

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


        binding.ivSearch.setOnClickListener()
        {
            Toast.makeText(context, "search bar clicked ", Toast.LENGTH_SHORT).show()
        }


    }


    private fun replaceFragment(f: Fragment) {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        ft.replace(R.id.mainFrame, f)
        ft.commit()
    }

    override fun onBackPressed() {
        finish()
    }


}


