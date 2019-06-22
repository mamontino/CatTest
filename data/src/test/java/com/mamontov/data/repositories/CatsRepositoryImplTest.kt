package com.mamontov.data.repositories

import com.mamontov.data.converters.Converter
import com.mamontov.data.database.CatEntity
import com.mamontov.data.datasources.CatsLocalDataSource
import com.mamontov.data.datasources.CatsRemoteDataSource
import com.mamontov.data.models.CatModel
import com.mamontov.data.reposirory.CatsRepositoryImpl
import com.mamontov.domain.entities.Cat
import com.mamontov.domain.reposirory.CatsRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import io.reactivex.rxkotlin.toSingle
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CatsRepositoryImplTest {

    private val catsRemoteDataSource: CatsRemoteDataSource = mock()
    private val catsLocalDataSource: CatsLocalDataSource = mock()
    private val catModelConverter: Converter<List<CatModel>, List<Cat>> = mock()
    private val catEntityConverter: Converter<List<CatEntity>, List<Cat>> = mock()

    private val repository: CatsRepository = CatsRepositoryImpl(
            catsRemoteDataSource,
            catsLocalDataSource,
            catModelConverter,
            catEntityConverter
    )

    @Test
    fun `WHEN add to favorites EXPECT complete`() {
        val id = "ID"
        val url = "URL"
        val catEntity = CatEntity(id, url)

        whenever(catsLocalDataSource.addToFavorites(catEntity)).thenReturn(Completable.complete())

        repository.addToFavorites(id, url)
                .test()
                .assertComplete()

        verify(catsLocalDataSource).addToFavorites(catEntity)
    }

    @Test
    fun `WHEN remove from favorites EXPECT complete`() {
        val id = "ID"
        val url = "URL"
        val cat = Cat(id, url)
        val catEntity = CatEntity(id, url)

        whenever(catsLocalDataSource.removeFromFavorites(catEntity)).thenReturn(Completable.complete())

        repository.removeFromFavorites(cat)
                .test()
                .assertComplete()

        verify(catsLocalDataSource).removeFromFavorites(catEntity)
    }

    @Test
    fun `WHEN get favourites EXPECT call from dataSource`() {
        val cats = emptyList<Cat>()
        val catEntities = emptyList<CatEntity>()

        whenever(catsLocalDataSource.getFavorites()).thenReturn(catEntities.toSingle())
        whenever(catEntityConverter.convert(catEntities)).thenReturn(cats)

        repository.getFavorites().test().assertNoErrors()

        verify(catsLocalDataSource).getFavorites()
        verify(catEntityConverter).convert(catEntities)
    }

    @Test
    fun `WHEN get cats EXPECT call from dataSource`() {
        val page = 1
        val cats = emptyList<Cat>()
        val catModels = emptyList<CatModel>()

        whenever(catsRemoteDataSource.getCats(page)).thenReturn(catModels.toSingle())
        whenever(catModelConverter.convert(catModels)).thenReturn(cats)

        repository.getCats(page).test().assertNoErrors()

        verify(catsRemoteDataSource).getCats(page)
        verify(catModelConverter).convert(catModels)
    }
}