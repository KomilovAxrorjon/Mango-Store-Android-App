package com.example.headphones.data

import android.util.Log
import com.example.headphones.data.dataClasses.Headphones
import com.example.headphones.data.dataClasses.NewHeadphones
import com.example.headphones.data.network.DeleteItemResponse
import com.example.headphones.data.network.HeadphonesItemResponse
import com.example.headphones.data.network.ListResponse
import com.example.headphones.data.network.NewResponse
import com.example.headphones.data.network.RetrofitInstance
import com.example.headphones.data.network.headphones.HeadphoneRequest
import com.example.headphones.data.network.headphones.HeadphoneRespone

class HeadphonesRepository {

    suspend fun getAllItems(): List<Headphones> {

        val headphones = mutableListOf<Headphones>()
        try {
            val response: ListResponse<HeadphoneRespone> =
                RetrofitInstance.headphonesService.getAllItems("00013851_mango")
            val headphonesFromResponse = response.data

            if(headphonesFromResponse != null){
                for (headphoneFromResponse in headphonesFromResponse){
                    headphones.add(
                        Headphones(
                            id = headphoneFromResponse.id,
                            product = headphoneFromResponse.product,
                            brand = headphoneFromResponse.brand,
                            warranty = headphoneFromResponse.warranty,
                            type = headphoneFromResponse.type,
                            price = headphoneFromResponse.price,
                            url = headphoneFromResponse.url,
                        )
                    )
                }
            }
        } catch (ex: Exception){
            ex.printStackTrace()
        }
        return headphones
    }

    suspend fun getItemById(headphoneId: String): NewHeadphones? {

        try {
            val response: HeadphonesItemResponse<HeadphoneRespone> =
                RetrofitInstance.headphonesService.getItemById(headphoneId, "00013851_mango")
            val itemFromResponse = response.data

            if(itemFromResponse != null){
                return NewHeadphones(
                    id = headphoneId,
                    product = itemFromResponse.product,
                    brand = itemFromResponse.brand,
                    warranty = itemFromResponse.warranty,
                    type = itemFromResponse.type,
                    price = itemFromResponse.price,
                    url = itemFromResponse.url,
                )
            }
        } catch (ex: Exception){
            ex.printStackTrace()
        }
        return null
    }

    suspend fun addNewItem(headphone: NewHeadphones): NewResponse? {
        var response: NewResponse

        try {
            val headphoneRequest =
                HeadphoneRequest(
                    headphone.product,
                    headphone.brand,
                    headphone.warranty,
                    headphone.type,
                    headphone.price,
                    headphone.url
                )
            response = RetrofitInstance.headphonesService.addNewItem(
                "00013851_mango",
                headphoneRequest
            )
            Log.d("Update_response", response.toString())
        } catch (ex: Exception){
            ex.printStackTrace()
            return null
        }
        return response
    }


    suspend fun updateItem (headphoneId: String, headphone: NewHeadphones): NewResponse? {
        var response: NewResponse

        try {
            val headphoneRequest =
                HeadphoneRequest(
                    headphone.product,
                    headphone.brand,
                    headphone.warranty,
                    headphone.type,
                    headphone.price,
                    headphone.url
                )
            response = RetrofitInstance.headphonesService.updateItem(
                headphoneId,
                "00013851_mango",
                headphoneRequest
            )
            Log.d("Update_response", response.toString())
        } catch (ex: Exception){
            ex.printStackTrace()
            return null
        }
        return response
    }
    suspend fun deleteItem(headphonesId: String): Boolean {
        try {

            val response: DeleteItemResponse = RetrofitInstance.headphonesService.deleteItemById(headphonesId, "00013851_mango")
            if (response.message == "Deleted!") {
                return true
            } else {

                println("Failed to delete the Item. Response code: ${response.code}")
            }

        } catch (ex: Exception) {
            ex.printStackTrace()

        }
        return false
    }



}