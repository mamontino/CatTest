package com.mamontov.data.datasources

import com.mamontov.data.models.CatModel
import com.mamontov.data.network.CatApi
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

interface CatsRemoteDataSource {

    fun getCats(page: Int?): Single<List<CatModel>>
}

class CatsRemoteDataSourceImpl @Inject constructor(
        private val catApi: CatApi
) : CatsRemoteDataSource {

    private companion object {
        const val LIMIT = 30
    }

    override fun getCats(page: Int?): Single<List<CatModel>> =
            catApi.getCats(LIMIT, page)
                    .subscribeOn(Schedulers.io())
}