package com.shahbozbek.museumwarehouse.data.repository

import android.content.Context
import com.shahbozbek.museumwarehouse.data.local.Items
import com.shahbozbek.museumwarehouse.data.local.ItemsDao
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val itemsDao: ItemsDao,
    @ApplicationContext private val context: Context
): Repository {

    private val prefs = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    override suspend fun getAll(): List<Items> {
        return itemsDao.getAll()
    }

    override suspend fun getById(id: Int): Items? {
        return itemsDao.getById(id)
    }

    override suspend fun update(item: Items) {
        itemsDao.update(item)
    }

    override suspend fun insert(item: Items) {
        itemsDao.insert(item)
    }

    override fun getLanguage(): String {
        return prefs.getString("language", "en") ?: "en"
    }

    override fun setLanguage(language: String) {
        prefs.edit().putString("language", language).apply()
    }

}