package com.mamontov.data.datasources

import com.mamontov.data.database.CatDao
import com.mamontov.data.database.CatEntity
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

interface CatsLocalDataSource {

    fun removeFromFavorites(cat: CatEntity): Completable

    fun addToFavorites(cat: CatEntity): Completable

    fun getFavorites(): Single<List<CatEntity>>
}

class CatsLocalDataSourceImpl @Inject constructor(
        private val catDao: CatDao
) : CatsLocalDataSource {

    override fun removeFromFavorites(cat: CatEntity): Completable =
            catDao.deleteCat(cat)
                    .subscribeOn(Schedulers.io())

    override fun addToFavorites(cat: CatEntity): Completable =
            catDao.insertCat(cat)
                    .subscribeOn(Schedulers.io())

    override fun getFavorites(): Single<List<CatEntity>> =
            catDao.getAllCats()
                    .subscribeOn(Schedulers.io())
}