package com.mamontov.presentation.cats

import com.arellomobile.mvp.InjectViewState
import com.mamontov.domain.entities.Cat
import com.mamontov.domain.usecases.AddToFavouritesUseCase
import com.mamontov.domain.usecases.GetCatsUseCase
import com.mamontov.domain.usecases.RemoveFromFavouritesUseCase
import com.mamontov.domain.usecases.SaveImageUseCase
import com.mamontov.presentation.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

@InjectViewState
class CatsPresenter @Inject constructor(
    private val getCatsUseCase: GetCatsUseCase,
    private val removeFromFavouritesUseCase: RemoveFromFavouritesUseCase,
    private val addToFavouritesUseCase: AddToFavouritesUseCase,
    private val saveImageUseCase: SaveImageUseCase
) : BasePresenter<CatsView>() {

    private var currentPage = 0
    private var cat: Cat? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.showLoading()
        loadCats(currentPage)
    }

    private fun loadCats(page: Int) {
        compositeDisposable.add(
            getCatsUseCase(page)
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(viewState::hideLoading)
                .doFinally(viewState::hideRefreshing)
                .subscribe(this::handleResponse, this::handleError)
        )
    }

    private fun handleResponse(cats: List<Cat>) {
        currentPage++
        if (cats.isEmpty()) {
            viewState.showEmptyCats()
        } else {
            viewState.addCats(cats)
        }
    }

    private fun handleError(e: Throwable) {
        viewState.showError(e.localizedMessage)
    }

    fun onFavoritesClicked(position: Int, cat: Cat?) {
        cat?.let {
            if (cat.favourite) {
                removeFromFavourites(position, cat)
            } else {
                addToFavourites(position, cat)
            }
        }
    }

    private fun removeFromFavourites(position: Int, cat: Cat) {
        val newCat = Cat(cat.id, cat.url, !cat.favourite)
        compositeDisposable.add(
            removeFromFavouritesUseCase(newCat)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { updateFavorites(position, newCat) },
                    this::handleError
                )
        )
    }


    private fun addToFavourites(position: Int, cat: Cat) {
        val newCat = Cat(cat.id, cat.url, !cat.favourite)
        compositeDisposable.add(
            addToFavouritesUseCase(cat.id, cat.url, !cat.favourite)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { updateFavorites(position, newCat) },
                    this::handleError
                )
        )
    }

    private fun updateFavorites(position: Int, cat: Cat) {
        viewState.updateFavoriteItem(position, cat)
    }

    fun loadMore() {
        loadCats(currentPage)
    }

    fun refreshCats() {
        currentPage = 0
        viewState.clearCats()
        loadMore()
    }

    fun onImageClicked(cat: Cat?) {
        cat?.let {
            this.cat = cat
            viewState.checkPermission(cat)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        currentPage = 0
        cat = null
    }

    fun permissionGranted() {
        cat?.url?.let {
            saveImageUseCase(it)
        }
        viewState.showMessage("Loading...")
        cat = null
    }
}