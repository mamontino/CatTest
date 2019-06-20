package com.mamontov.domain.usecases

import com.mamontov.domain.entities.Cat
import com.mamontov.domain.reposirory.CatsRepository
import io.reactivex.Single
import javax.inject.Inject

interface GetFavoritesUseCase {

    operator fun invoke(): Single<List<Cat>>
}

class GetFavoritesUseCaseImpl @Inject constructor(
    private val catsRepository: CatsRepository
) : GetFavoritesUseCase {

    override fun invoke(): Single<List<Cat>> =
        catsRepository.getFavorites()
}