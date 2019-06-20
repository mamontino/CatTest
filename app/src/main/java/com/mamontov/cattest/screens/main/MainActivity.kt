package com.mamontov.cattest.screens.main

import android.content.Context
import android.os.Bundle
import com.mamontov.cattest.R
import com.mamontov.cattest.screens.BaseActivity
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        initToolbar()
        initPageAdapter(this)
    }

    private fun initToolbar() {
        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_back_white)

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun initPageAdapter(context: Context) {
        val adapter = SectionsPagerAdapter(context, supportFragmentManager)
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
    }
}
