package com.mamontov.data.datasources

import com.mamontov.data.database.CatDao
import com.mamontov.data.database.CatEntity
import com.mamontov.data.models.CatModel
import com.mamontov.data.network.CatApi
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

interface CatsDataSource {

    fun removeFromFavorites(cat: CatEntity): Completable

    fun addToFavorites(cat: CatEntity): Completable

    fun getFavorites(): Single<List<CatEntity>>

    fun getCats(page: Int?): Single<List<CatModel>>
}

class CatsDataSourceImpl @Inject constructor(
    private val catApi: CatApi,
    private val catDao: CatDao
) : CatsDataSource {

    private companion object {
        const val LIMIT = 30
    }

    override fun removeFromFavorites(cat: CatEntity): Completable =
        catDao.deleteCat(cat)
            .subscribeOn(Schedulers.io())

    override fun addToFavorites(cat: CatEntity): Completable =
        catDao.insertCat(cat)
            .subscribeOn(Schedulers.io())

    override fun getFavorites(): Single<List<CatEntity>> =
        catDao.getAllCats()
            .subscribeOn(Schedulers.io())

    override fun getCats(page: Int?): Single<List<CatModel>> =
        catApi.getCats(LIMIT, page)
            .subscribeOn(Schedulers.io())
}