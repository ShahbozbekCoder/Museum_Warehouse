package com.shahbozbek.museumwarehouse.data.repository

import com.shahbozbek.museumwarehouse.data.local.Items
import com.shahbozbek.museumwarehouse.data.local.ItemsDao
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val itemsDao: ItemsDao
): Repository {
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

}