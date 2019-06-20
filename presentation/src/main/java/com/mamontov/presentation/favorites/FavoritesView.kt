package com.mamontov.presentation.favorites

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.mamontov.domain.entities.Cat

@StateStrategyType(AddToEndSingleStrategy::class)
interface FavoritesView : MvpView {

    fun addCats(cats: List<Cat>)

    fun removeFavoriteItem(cat: Cat)

    fun showLoading()

    fun hideLoading()

    fun showEmptyCats()
}