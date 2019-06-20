package com.mamontov.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cats")
data class CatEntity(@PrimaryKey
                     @ColumnInfo(name = "id") var id: String,
                     @ColumnInfo(name = "url") var url: String)
