package com.mamontov.domain.usecases

import com.mamontov.domain.entities.Cat
import com.mamontov.domain.reposirory.CatsRepository
import io.reactivex.Single
import javax.inject.Inject

interface GetFavouritesUseCase {

    operator fun invoke(): Single<List<Cat>>
}

class GetFavouritesUseCaseImpl @Inject constructor(
    private val catsRepository: CatsRepository
) : GetFavouritesUseCase {

    override fun invoke(): Single<List<Cat>> =
        catsRepository.getFavorites()
}