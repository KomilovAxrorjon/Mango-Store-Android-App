package com.example.headphones.data.network

import com.google.gson.annotations.SerializedName

data class DeleteItemResponse (
    @SerializedName("code")
    val code: String = "",
    @SerializedName("status")
    val status: String = "",
    @SerializedName("message")
    val message: String = "",
)