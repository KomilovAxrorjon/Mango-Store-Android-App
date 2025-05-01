package com.example.headphones.delete

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.headphones.data.HeadphonesRepository
import kotlinx.coroutines.launch

class DeleteViewModel(
    headphonesId: String,
    private val headphonesRepository: HeadphonesRepository
): ViewModel(){

        init {
            deleteHeadphonesById(headphonesId)
        }

        fun deleteHeadphonesById(headphonesId: String){
        viewModelScope.launch{
            headphonesRepository.deleteItem(headphonesId)
        }
    }
}