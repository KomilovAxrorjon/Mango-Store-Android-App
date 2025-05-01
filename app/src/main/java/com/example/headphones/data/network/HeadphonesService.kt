package com.example.headphones.data.network

import com.example.headphones.data.network.headphones.HeadphoneRequest
import com.example.headphones.data.network.headphones.HeadphoneRespone
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface HeadphonesService {@GET("records/all")
suspend fun getAllItems(@Query("student_id") student_id: String):
        ListResponse<HeadphoneRespone>

    @POST("records")
    suspend fun addNewItem(
        @Query("student_id") student_id: String,
        @Body headphoneRequest: HeadphoneRequest
    ): NewResponse

    @GET("records/{record_id}")
    suspend fun getItemById(
        @Path("record_id") record_id: String,
        @Query("student_id") student_id: String,
        ): HeadphonesItemResponse<HeadphoneRespone>

    @PUT("records/{record_id}")
    suspend fun updateItem(
        @Path("record_id") record_id: String,
        @Query("student_id") student_id: String,
        @Body headphoneRequest: HeadphoneRequest
    ): NewResponse

    @DELETE("records/{record_id}")
    suspend fun deleteItemById(
        @Path("record_id") record_id: String,
        @Query("student_id") student_id: String
    ): DeleteItemResponse

}