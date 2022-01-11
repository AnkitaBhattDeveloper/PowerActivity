package com.example.poweractivity.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.poweractivity.R
import com.example.poweractivity.activities.MainActivity
import com.example.poweractivity.fragments.*

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle, private val context: Context, ) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    private val activity: MainActivity = context as MainActivity
    private val mFragmentList: List<Fragment> = ArrayList()
    override fun getItemCount(): Int {
        return 5
    }
     override fun createFragment(position: Int): Fragment {
          return when (position) {
              0 -> {
                 //activity.selectBottomNavigationItem(R.id.menuDetails)
                  ProductDetailFragment()
              }
              1 -> {
                 // activity.selectBottomNavigationItem(R.id.menuBuy)
                  BuyFragment()

              }
              2 -> {
                // activity.selectBottomNavigationItem(R.id.menuHome)
                  HomeFragment()

              }
              3 -> {
                 // activity.selectBottomNavigationItem(R.id.menuGraph)
                  GraphFragment()
              }
              4 -> {
                 // activity.selectBottomNavigationItem(R.id.menuSettings)
                  SettingFragment()
              }
              else -> {
                  BuyFragment()
              }
          }

      }
    fun addFragment(fragment: Fragment?) {
        if (fragment != null) {
            mFragmentList.toMutableList().add(fragment)
        }
    }
}