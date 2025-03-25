package com.shahbozbek.museumwarehouse.data.repository

import com.shahbozbek.museumwarehouse.data.local.Items

interface Repository {

    suspend fun getAll(): List<Items>

    suspend fun getById(id: Int): Items?

    suspend fun update(item: Items)

    suspend fun insert(item: Items)

}