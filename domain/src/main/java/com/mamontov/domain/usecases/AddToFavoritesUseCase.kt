package com.mamontov.domain.usecases

import com.mamontov.domain.entities.Cat
import com.mamontov.domain.reposirory.CatsRepository
import io.reactivex.Completable
import javax.inject.Inject

interface AddToFavoritesUseCase {

    operator fun invoke(cat: Cat): Completable
}

class AddToFavoritesUseCaseImpl @Inject constructor(
    private val catsRepository: CatsRepository
) : AddToFavoritesUseCase {

    override fun invoke(cat: Cat): Completable =
        catsRepository.addToFavorites(cat)
}