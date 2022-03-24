package com.example.poweractivity.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.poweractivity.fragments.*

class viewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    val context: Context,
) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 5
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                ProductDetailFragment()
            }
            1 -> {
                BuyFragment()
            }
            2 -> {
                HomeFragment()
            }
            3 -> {
                GraphFragment()
            }
            4 -> {
                SettingFragment()
            }
            else ->
                HomeFragment()
        }

    }


}