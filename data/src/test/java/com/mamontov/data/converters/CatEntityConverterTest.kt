package com.mamontov.data.converters

import com.mamontov.data.database.CatEntity
import com.mamontov.domain.entities.Cat
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CatEntityConverterTest {

    private val converter = CatEntityConverter()

    @Test
    fun convert() {
        val id = "ID"
        val url = "URL"
        val favourite = true
        val cat = Cat(id, url, favourite)
        val catEntity = CatEntity(id, url)

        val convertedCat = converter.convert(catEntity)

        assertEquals(cat, convertedCat)
    }
}