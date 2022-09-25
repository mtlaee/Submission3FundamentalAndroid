package com.example.submission2aplikasigithubtaufikakbar.Adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.submission2aplikasigithubtaufikakbar.FragmentFollower
import com.example.submission2aplikasigithubtaufikakbar.FragmentFollowing

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle, data: Bundle): FragmentStateAdapter(fragmentManager, lifecycle) {
    private var fragmentBundle: Bundle
    init {
        fragmentBundle=data
    }
    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FragmentFollower()
            1 -> fragment = FragmentFollowing()
        }
        fragment?.arguments=this.fragmentBundle
        return fragment as Fragment

    }
    override fun getItemCount(): Int =2
}
