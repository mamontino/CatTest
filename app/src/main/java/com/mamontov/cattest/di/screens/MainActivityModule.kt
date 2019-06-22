package com.mamontov.cattest.di.screens

import com.mamontov.cattest.di.FragmentScope
import com.mamontov.cattest.screens.cats.CatsFragment
import com.mamontov.cattest.screens.favorites.FavouritesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MainActivityModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [CatsFragmentModule::class])
    fun provideCatsFragment(): CatsFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [FavoritesFragmentModule::class])
    fun provideFavotitesFragment(): FavouritesFragment
}
