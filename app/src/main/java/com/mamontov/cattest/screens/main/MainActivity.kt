package com.mamontov.cattest.screens.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mamontov.cattest.R
import com.mamontov.cattest.screens.BaseActivity
import com.mamontov.cattest.screens.cats.CatsFragment
import com.mamontov.cattest.screens.favorites.FavoritesFragment
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : BaseActivity() {

    private val onNavigationItemSelectedListener =
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.navigation_home -> {
                        toolbar.title = this.getString(R.string.tab_text_cats)
                        openFragment(CatsFragment.newInstance())
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.navigation_favourites -> {
                        toolbar.title = this.getString(R.string.tab_text_favorites)
                        openFragment(FavoritesFragment.newInstance())
                        return@OnNavigationItemSelectedListener true
                    }
                }
                false
            }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        initViews()
    }

    private fun initViews() {
        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_back_white)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        navView.menu.getItem(0).isChecked = true
        openFragment(CatsFragment.newInstance())
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .disallowAddToBackStack()
                .commit()
    }
}
