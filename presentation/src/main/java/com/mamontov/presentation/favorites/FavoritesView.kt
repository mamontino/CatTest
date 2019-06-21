package com.mamontov.presentation.favorites

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.mamontov.domain.entities.Cat

@StateStrategyType(AddToEndSingleStrategy::class)
interface FavoritesView : MvpView {

    fun addCats(cats: List<Cat>)

    fun removeFavoriteItem(position: Int)

    fun showLoading()

    fun hideLoading()

    fun showEmptyCats()

    fun showError(message: String)

    fun checkPermission(cat: Cat)

    fun showMessage(message: String)
}