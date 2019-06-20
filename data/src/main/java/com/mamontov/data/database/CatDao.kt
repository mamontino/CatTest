package com.mamontov.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface CatDao {

    @Query("SELECT COUNT(*) from cats")
    fun count(): Int

    @Query("select * from cats")
    fun getAllCats(): Single<List<CatEntity>>

    @Insert(onConflict = REPLACE)
    fun insertCat(cat: CatEntity): Completable

    @Delete
    fun deleteCat(cat: CatEntity): Completable
}