package com.mamontov.cattest.screens.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mamontov.cattest.R
import com.mamontov.cattest.screens.BaseActivity
import com.mamontov.cattest.screens.cats.CatsFragment
import com.mamontov.cattest.screens.favorites.FavouritesFragment
import com.mamontov.presentation.main.MainPresenter
import com.mamontov.presentation.main.MainView
import kotlinx.android.synthetic.main.main_activity.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView {

    private val onNavigationItemSelectedListener =
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.navigation_home -> {
                        presenter.showCats()
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.navigation_favourites -> {
                        presenter.showFavourites()
                        return@OnNavigationItemSelectedListener true
                    }
                }
                false
            }

    @Inject
    @InjectPresenter
    lateinit var presenter: MainPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }

    override fun setUp() {
        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_back_white)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        navView.selectedItemId = 0
        navView.menu.getItem(0).isChecked = true
        openFragment(CatsFragment.newInstance())
    }

    override fun showFavourites() {
        toolbar.title = this.getString(R.string.tab_text_favorites)
        openFragment(FavouritesFragment.newInstance())
    }

    override fun showCats() {
        toolbar.title = this.getString(R.string.tab_text_cats)
        openFragment(CatsFragment.newInstance())
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .disallowAddToBackStack()
                .commit()
    }
}
