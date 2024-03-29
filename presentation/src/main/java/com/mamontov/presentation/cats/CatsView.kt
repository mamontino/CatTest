package com.mamontov.presentation.cats

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.mamontov.domain.entities.Cat

@StateStrategyType(AddToEndSingleStrategy::class)
interface CatsView : MvpView {

    fun updateFavoriteItem(position: Int, cat: Cat)

    fun addCats(cats: List<Cat>)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showLoading()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun hideLoading()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showError(message: String)

    fun showEmptyCats()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun clearCats()

    fun hideRefreshing()

    fun showMessage(message: String)

    fun imageClicked()
}