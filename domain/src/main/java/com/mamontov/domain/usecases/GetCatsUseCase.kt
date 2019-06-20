package com.mamontov.domain.usecases

import com.mamontov.domain.entities.Cat
import com.mamontov.domain.reposirory.CatsRepository
import io.reactivex.Single
import javax.inject.Inject

interface GetCatsUseCase {

    operator fun invoke(page: Int): Single<List<Cat>>
}

class GetCatsUseCaseImpl @Inject constructor(
    private val catsRepository: CatsRepository
) : GetCatsUseCase {

    override fun invoke(page: Int): Single<List<Cat>> =
        catsRepository.getCats(page)
}