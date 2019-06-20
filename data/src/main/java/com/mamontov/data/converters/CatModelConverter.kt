package com.mamontov.data.converters

import com.mamontov.data.models.CatModel
import com.mamontov.domain.entities.Cat
import javax.inject.Inject

class CatModelConverter @Inject constructor() : Converter<CatModel, Cat> {

    override fun convert(from: CatModel): Cat =
            Cat(from.id, from.url)
}
