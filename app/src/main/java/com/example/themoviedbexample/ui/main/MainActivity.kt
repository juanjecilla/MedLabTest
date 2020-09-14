package com.example.themoviedbexample.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.themoviedbexample.R
import com.example.themoviedbexample.base.BaseFragmentActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import javax.inject.Inject

class MainActivity : BaseFragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pagerAdapter = MainPagerAdapter(supportFragmentManager)
        view_pager.setPagingEnabled(false)

        view_pager.adapter = pagerAdapter
        view_pager.offscreenPageLimit = 1

        bottom_navigation_bar.setTextVisibility(false)
        bottom_navigation_bar.enableAnimation(false)
        bottom_navigation_bar.setIconSize(28f, 28f)
        bottom_navigation_bar.setupWithViewPager(view_pager)
    }
}
