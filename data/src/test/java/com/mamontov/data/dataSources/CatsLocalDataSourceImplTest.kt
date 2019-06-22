package com.mamontov.data.dataSources

import com.mamontov.data.database.CatDao
import com.mamontov.data.database.CatEntity
import com.mamontov.data.datasources.CatsLocalDataSource
import com.mamontov.data.datasources.CatsLocalDataSourceImpl
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import io.reactivex.rxkotlin.toSingle
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CatsLocalDataSourceImplTest {

    private val database = mock<CatDao>()
    private val catsLocalDataSource: CatsLocalDataSource = CatsLocalDataSourceImpl(database)

    @Test
    fun `WHEN add to favorites EXPECT complete`() {
        val id = "ID"
        val url = "URL"
        val catEntity = CatEntity(id, url)

        whenever(database.insertCat(catEntity)).thenReturn(Completable.complete())

        catsLocalDataSource.addToFavorites(catEntity)
                .test()

        verify(database).insertCat(catEntity)
    }

    @Test
    fun `WHEN remove from favorites EXPECT complete`() {
        val id = "ID"
        val url = "URL"
        val catEntity = CatEntity(id, url)

        whenever(database.deleteCat(catEntity)).thenReturn(Completable.complete())

        catsLocalDataSource.removeFromFavorites(catEntity)
                .test()

        verify(database).deleteCat(catEntity)
    }

    @Test
    fun `WHEN get favourites EXPECT call from dataSource`() {
        val cats = emptyList<CatEntity>().toSingle()

        whenever(database.getAllCats()).thenReturn(cats)

        catsLocalDataSource.getFavorites()
                .test()
                .assertNoErrors()

        verify(database).getAllCats()
    }
}