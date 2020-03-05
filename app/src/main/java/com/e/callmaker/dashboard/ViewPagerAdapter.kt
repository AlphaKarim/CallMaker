package com.e.callmaker.dashboard

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(private val myContext: Context, fm: FragmentManager, internal var totalTabs: Int) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> CallLogFragment()
            1 -> DialNumberFragment()
            else -> ContactFragment()
        }

    }

    override fun getCount(): Int {
        return totalTabs
    }

}
