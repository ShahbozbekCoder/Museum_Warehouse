package com.shahbozbek.museumwarehouse.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "items")
data class Items(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val image: String,
    val status: Boolean
): Serializable
