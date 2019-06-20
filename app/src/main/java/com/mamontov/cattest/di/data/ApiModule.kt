package com.mamontov.cattest.di.data

import com.mamontov.cattest.di.AppScope
import com.mamontov.data.network.CatApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(includes = [RetrofitModule::class])
class ApiModule {


    @Provides
    @AppScope
    fun provideApi(retrofit: Retrofit): CatApi =
            retrofit.create()

    private inline fun <reified T : Any> Retrofit.create() =
            create(T::class.java)
}