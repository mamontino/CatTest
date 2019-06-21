package com.mamontov.domain.usecases

import com.mamontov.domain.entities.Cat
import com.mamontov.domain.reposirory.CatsRepository
import io.reactivex.Completable
import javax.inject.Inject

interface RemoveFromFavouritesUseCase {

    operator fun invoke(cat: Cat): Completable
}

class RemoveFromFavouritesUseCaseImpl @Inject constructor(
    private val catsRepository: CatsRepository
) : RemoveFromFavouritesUseCase {

    override fun invoke(cat: Cat): Completable =
        catsRepository.removeFromFavorites(cat)
}