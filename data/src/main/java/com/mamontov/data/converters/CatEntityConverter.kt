package com.mamontov.data.converters

import com.mamontov.data.database.CatEntity
import com.mamontov.domain.entities.Cat
import javax.inject.Inject

class CatEntityConverter @Inject constructor() : TwoSideConverter<Cat, CatEntity> {

    override fun convert(from: Cat): CatEntity =
        CatEntity(from.id, from.url)

    override fun revert(to: CatEntity): Cat =
        Cat(to.id, to.url)
}
