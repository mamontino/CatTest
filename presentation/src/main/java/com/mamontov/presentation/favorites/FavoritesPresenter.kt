package com.mamontov.presentation.favorites

import com.arellomobile.mvp.InjectViewState
import com.mamontov.domain.entities.Cat
import com.mamontov.domain.logError
import com.mamontov.domain.usecases.GetFavoritesUseCase
import com.mamontov.domain.usecases.RemoveFromFavoritesUseCase
import com.mamontov.domain.usecases.SaveImageUseCase
import com.mamontov.presentation.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

@InjectViewState
class FavoritesPresenter @Inject constructor(
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val removeFromFavoritesUseCase: RemoveFromFavoritesUseCase,
    private val saveImageUseCase: SaveImageUseCase
) : BasePresenter<FavoritesView>() {

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

    private fun handleResponse(cats: List<Cat>){
        if (cats.isEmpty()){
            viewState.showEmptyCats()
        }else{
            viewState.addCats(cats)
        }
    }


    private fun handleError(e: Throwable){
        logError(e.localizedMessage)
    }

    fun onFavoritesClicked(cat: Cat?){
        cat?.let {
            compositeDisposable.add(
                removeFromFavoritesUseCase(cat)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        {updateFavorites(cat)},
                        this::handleError)
            )
        }
    }

    private fun updateFavorites(cat: Cat) {
       viewState.removeFavoriteItem(cat)
    }

    fun onImageClicked(cat: Cat?) {
        cat?.let {

        }
    }
}