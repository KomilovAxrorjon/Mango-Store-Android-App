package com.example.headphones.delete

import androidx.compose.runtime.Composable
import com.example.headphones.data.HeadphonesRepository


@Composable
fun DeleteItem(
    headphonesId: String,
    viewModel: DeleteViewModel = DeleteViewModel(headphonesId , HeadphonesRepository())

){
    try {
        if (viewModel.deleteHeadphonesById(headphonesId) == null) {
            viewModel.deleteHeadphonesById(headphonesId)
            println("Item deleted")
        } else {
            println("Item not deleted")
        }


    }catch (ex: Exception){
        ex.printStackTrace()
    }


}