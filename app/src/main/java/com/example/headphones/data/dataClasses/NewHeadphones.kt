package com.example.headphones.data.dataClasses

data class NewHeadphones(
    val id: String = "",
    val product: String,
    val brand: String,
    val warranty: String,
    val type: String,
    val price: Double,
    val url: String,
)
