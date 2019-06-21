package com.mamontov.domain.usecases

import com.mamontov.domain.reposirory.CatsRepository
import io.reactivex.Completable
import javax.inject.Inject

interface AddToFavouritesUseCase {

    operator fun invoke(id: String, url: String, favourite: Boolean): Completable
}

class AddToFavouritesUseCaseImpl @Inject constructor(
    private val catsRepository: CatsRepository
) : AddToFavouritesUseCase {

    override fun invoke(id: String, url: String, favourite: Boolean): Completable =
        catsRepository.addToFavorites(id, url, favourite)
}