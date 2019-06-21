package com.mamontov.presentation.favorites

import com.arellomobile.mvp.InjectViewState
import com.mamontov.domain.entities.Cat
import com.mamontov.domain.usecases.AddToFavouritesUseCase
import com.mamontov.domain.usecases.GetFavoritesUseCase
import com.mamontov.domain.usecases.RemoveFromFavouritesUseCase
import com.mamontov.domain.usecases.SaveImageUseCase
import com.mamontov.presentation.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

@InjectViewState
class FavoritesPresenter @Inject constructor(
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val removeFromFavouritesUseCase: RemoveFromFavouritesUseCase,
    private val addToFavouritesUseCase: AddToFavouritesUseCase,
    private val saveImageUseCase: SaveImageUseCase
) : BasePresenter<FavoritesView>() {

    private var cat: Cat? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.showLoading()
        getFavorites()
    }

    private fun getFavorites() {
        compositeDisposable.add(
            getFavoritesUseCase()
                .doFinally(viewState::hideLoading)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse, this::handleError)
        )
    }

    private fun handleResponse(cats: List<Cat>) {
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
                addToFavourites(position, cat)
            } else {
                removeFromFavourites(position, cat)
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

    fun onImageClicked(cat: Cat?) {
        cat?.let {
            this.cat = cat
            viewState.checkPermission(cat)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

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