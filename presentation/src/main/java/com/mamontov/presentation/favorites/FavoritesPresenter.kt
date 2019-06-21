package com.mamontov.presentation.favorites

import com.arellomobile.mvp.InjectViewState
import com.mamontov.domain.entities.Cat
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
        private val saveImageUseCase: SaveImageUseCase
) : BasePresenter<FavoritesView>() {

    private var cat: Cat? = null

    fun getFavorites() {
        viewState.showLoading()
        compositeDisposable.add(
                getFavoritesUseCase()
                        .observeOn(AndroidSchedulers.mainThread())
                        .doFinally(viewState::hideLoading)
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
            removeFromFavourites(position, cat)
        }
    }

    private fun removeFromFavourites(position: Int, cat: Cat) {
        compositeDisposable.add(
                removeFromFavouritesUseCase(cat)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { viewState.removeFavoriteItem(position) },
                                this::handleError
                        )
        )
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