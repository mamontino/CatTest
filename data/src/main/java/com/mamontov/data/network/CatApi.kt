package com.mamontov.data.network

import com.mamontov.data.models.CatModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CatApi {

    @GET("search?order=Desc")
    fun getCats(@Query("limit") limit: Int? = null,
                @Query("page") page: Int? = null): Single<List<CatModel>>
}