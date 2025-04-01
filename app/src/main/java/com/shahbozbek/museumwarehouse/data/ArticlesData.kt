package com.shahbozbek.museumwarehouse.data

import java.io.Serializable

data class ArticlesData(
    val title: String,
    val content: String,
    val image: Int
): Serializable
