package com.shahbozbek.museumwarehouse.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Items::class], version = 1, exportSchema = false)
abstract class ItemsDatabase: RoomDatabase() {

    abstract fun itemsDao(): ItemsDao

}