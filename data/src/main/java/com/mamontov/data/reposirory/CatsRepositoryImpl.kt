package com.mamontov.data.reposirory

import com.mamontov.data.converters.Converter
import com.mamontov.data.database.CatEntity
import com.mamontov.data.datasources.CatsDataSource
import com.mamontov.data.models.CatModel
import com.mamontov.domain.entities.Cat
import com.mamontov.domain.reposirory.CatsRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class CatsRepositoryImpl @Inject constructor(
    private val catsDataSource: CatsDataSource,
    private val catModelConverter: Converter<List<CatModel>, List<Cat>>,
    private val catEntityConverter: Converter<List<CatEntity>, List<Cat>>
) : CatsRepository {

    override fun removeFromFavorites(cat: Cat): Completable =
        catsDataSource.removeFromFavorites(CatEntity(cat.id, cat.url))

    override fun addToFavorites(id: String, url: String): Completable =
        catsDataSource.addToFavorites(CatEntity(id, url))

    override fun getFavorites(): Single<List<Cat>> =
        catsDataSource.getFavorites()
            .map(catEntityConverter::convert)

    override fun getCats(page: Int?): Single<List<Cat>> =
        catsDataSource.getCats(page)
            .map(catModelConverter::convert)
}