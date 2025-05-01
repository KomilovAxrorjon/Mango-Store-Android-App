package com.example.headphones.detailedPage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.headphones.data.HeadphonesRepository
import com.example.headphones.data.dataClasses.NewHeadphones
import kotlinx.coroutines.launch

class DetailedViewModel(
    headphonesId: String,
    private val headphonesRepository: HeadphonesRepository): ViewModel() {

        val headphonesLiveData: MutableLiveData<NewHeadphones> by lazy {
            MutableLiveData<NewHeadphones>()
        }
        init {
            getHeadphonesByIdFromDatabase(headphonesId)
        }

    private fun getHeadphonesByIdFromDatabase(headphonesId: String) {
        viewModelScope.launch {
            if (!headphonesId.isNullOrEmpty()) {
                val headphones = headphonesRepository.getItemById(headphonesId)
                headphonesLiveData.value = headphones
            }
        }
    }
}