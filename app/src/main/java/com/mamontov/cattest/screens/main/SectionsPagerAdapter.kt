package com.mamontov.cattest.screens.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.mamontov.cattest.R
import com.mamontov.cattest.screens.cats.CatsFragment
import com.mamontov.cattest.screens.favorites.FavouritesFragment

class SectionsPagerAdapter(val context: Context, manager: FragmentManager) : FragmentStatePagerAdapter(manager) {

    private companion object {
        const val ITEM_COUNT = 2
    }

    override fun getCount(): Int = ITEM_COUNT

    override fun getItem(position: Int): Fragment =
        if (position == 0) {
            CatsFragment.newInstance()
        } else {
            FavouritesFragment.newInstance()
        }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> context.getString(R.string.tab_text_cats)
            else -> context.getString(R.string.tab_text_favorites)
        }
    }
}