package com.mamontov.domain.reposirory

import com.mamontov.domain.entities.Cat
import io.reactivex.Completable
import io.reactivex.Single

interface CatsRepository {

    fun getCats(page: Int?): Single<List<Cat>>

    fun removeFromFavorites(cat: Cat): Completable

    fun addToFavorites(id: String, url: String): Completable

    fun getFavorites(): Single<List<Cat>>
}