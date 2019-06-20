package com.mamontov.data.reposirory

import com.mamontov.data.converters.Converter
import com.mamontov.data.database.CatEntity
import com.mamontov.data.datasources.CatsDataSource
import com.mamontov.data.models.CatModel
import com.mamontov.domain.entities.Cat
import com.mamontov.domain.reposirory.CatsRepository
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.rxkotlin.toSingle
import javax.inject.Inject

class CatsRepositoryImpl @Inject constructor(
    private val catsDataSource: CatsDataSource,
    private val catModelConverter: Converter<List<CatModel>, List<Cat>>,
    private val catEntityConverter: Converter<Cat, CatEntity>
) : CatsRepository {

    override fun removeFromFavorites(cat: Cat): Completable =
        Completable.complete()

    override fun addToFavorites(cat: Cat): Completable =
        Completable.complete()

    override fun getFavorites(): Single<List<Cat>> =
        emptyList<Cat>().toSingle()

    override fun getCats(page: Int?): Single<List<Cat>> =
        catsDataSource.getCats(page)
            .map(catModelConverter::convert)
}