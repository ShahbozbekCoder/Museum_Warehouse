package com.shahbozbek.museumwarehouse.di

import android.content.Context
import androidx.room.Room
import com.shahbozbek.museumwarehouse.data.local.ItemsDao
import com.shahbozbek.museumwarehouse.data.local.ItemsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @[Provides Singleton]
    fun provideItemsDatabase(@ApplicationContext applicationContext: Context): ItemsDatabase {
        return Room.databaseBuilder(
            applicationContext,
            ItemsDatabase::class.java,
            "items_database"
        ).build()
    }

    @[Provides Singleton]
    fun provideItemsDao(itemsDatabase: ItemsDatabase): ItemsDao {
        return itemsDatabase.itemsDao()
    }

}