package com.example.headphones.editPage

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.headphones.data.HeadphonesRepository
import com.example.headphones.data.dataClasses.NewHeadphones
import com.example.headphones.data.network.NewResponse
import kotlinx.coroutines.launch

class UpdateViewModel(
    private val headphonesRepository: HeadphonesRepository): ViewModel() {

    val updateResponseLiveData: MutableLiveData<NewResponse> by lazy {
        MutableLiveData<NewResponse>()
    }
    fun updateItem(headphonesId: String, headphones: NewHeadphones) {
        viewModelScope.launch {
            try {
                val response = headphonesRepository.updateItem(headphonesId, headphones)
                updateResponseLiveData.value = response

                Log.d("Update_response", response.toString())
            } catch (ex: Exception){
                ex.printStackTrace()
            }
        }
    }
}