package com.example.headphones.data.network

import com.google.gson.annotations.SerializedName

data class HeadphonesItemResponse<T>(
    @SerializedName("code")
    val code: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: T?
)
