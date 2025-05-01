package com.example.headphones.data.network.headphones

import com.google.gson.annotations.SerializedName

data class HeadphoneRequest(
    @SerializedName("title")
    val product: String,
    @SerializedName("description")
    val brand: String,
    @SerializedName("phone")
    val warranty: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("url")
    val url: String,
)
