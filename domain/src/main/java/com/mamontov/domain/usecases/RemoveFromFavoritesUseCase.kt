package com.mamontov.domain.usecases

import com.mamontov.domain.entities.Cat
import com.mamontov.domain.reposirory.CatsRepository
import io.reactivex.Completable
import javax.inject.Inject

interface RemoveFromFavoritesUseCase {

    operator fun invoke(cat: Cat): Completable
}

class RemoveFromFavoritesUseCaseImpl @Inject constructor(
    private val catsRepository: CatsRepository
) : RemoveFromFavoritesUseCase {

    override fun invoke(cat: Cat): Completable =
        catsRepository.removeFromFavorites(cat)
}