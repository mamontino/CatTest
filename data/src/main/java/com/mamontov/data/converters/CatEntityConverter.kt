package com.mamontov.data.converters

import com.mamontov.data.database.CatEntity
import com.mamontov.domain.entities.Cat
import javax.inject.Inject

class CatEntityConverter @Inject constructor() : Converter<CatEntity, Cat> {

    override fun convert(from: CatEntity): Cat =
        Cat(from.id, from.url, favourite = true)
}
