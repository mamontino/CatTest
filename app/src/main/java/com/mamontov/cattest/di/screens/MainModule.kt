package com.mamontov.cattest.di.screens

import com.mamontov.cattest.di.AppScope
import com.mamontov.data.converters.CatEntityConverter
import com.mamontov.data.converters.CatModelConverter
import com.mamontov.data.converters.Converter
import com.mamontov.data.converters.ListConverter
import com.mamontov.data.database.CatEntity
import com.mamontov.data.datasources.CatsDataSource
import com.mamontov.data.datasources.CatsDataSourceImpl
import com.mamontov.data.datasources.ImageDataSource
import com.mamontov.data.datasources.ImageDataSourceImpl
import com.mamontov.data.models.CatModel
import com.mamontov.data.reposirory.CatsRepositoryImpl
import com.mamontov.data.reposirory.ImageRepositoryImpl
import com.mamontov.domain.entities.Cat
import com.mamontov.domain.reposirory.CatsRepository
import com.mamontov.domain.reposirory.ImageRepository
import dagger.Binds
import dagger.Module

@Module
interface MainModule {

    @Binds
    @AppScope
    fun bindCatsRepository(repository: CatsRepositoryImpl): CatsRepository

    @Binds
    @AppScope
    fun bindImageRepository(repository: ImageRepositoryImpl): ImageRepository

    @Binds
    @AppScope
    fun bindCatsDataSource(dataSource: CatsDataSourceImpl): CatsDataSource

    @Binds
    @AppScope
    fun bindImageDataSource(dataSource: ImageDataSourceImpl): ImageDataSource

    @Binds
    @AppScope
    fun provideCatModelListConverter(converter: ListConverter<CatModel, Cat>): Converter<List<CatModel>, List<Cat>>

    @Binds
    @AppScope
    fun bindCatModelConverter(converter: CatModelConverter): Converter<CatModel, Cat>

    @Binds
    @AppScope
    fun provideCatEntityListConverter(converter: ListConverter<CatEntity, Cat>): Converter<List<CatEntity>, List<Cat>>

    @Binds
    @AppScope
    fun provideCatEntityConverter(converter: CatEntityConverter): Converter<CatEntity, Cat>
}
