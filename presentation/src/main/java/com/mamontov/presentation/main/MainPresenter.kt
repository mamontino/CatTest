package com.mamontov.presentation.main

import com.arellomobile.mvp.InjectViewState
import com.mamontov.presentation.BasePresenter
import javax.inject.Inject

@InjectViewState
class MainPresenter @Inject constructor(
) : BasePresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.setUp()
    }

    fun showCats() {
        viewState.showCats()
    }

    fun showFavourites() {
        viewState.showFavourites()
    }
}