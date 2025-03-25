package com.shahbozbek.museumwarehouse.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ItemsDao {

    @Query("SELECT * FROM items")
    suspend fun getAll(): List<Items>

    @Query("SELECT * FROM items WHERE id = :id")
    suspend fun getById(id: Int): Items?

    @Update
    suspend fun update(item: Items)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: Items)

}