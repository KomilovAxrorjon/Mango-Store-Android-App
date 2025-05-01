package com.example.headphones.homePage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.headphones.data.HeadphonesRepository
import com.example.headphones.data.dataClasses.Headphones
import kotlinx.coroutines.launch

class HeadphonesListViewModel(private val headphonesRepository: HeadphonesRepository) :
    ViewModel() {

    val headphonesLiveData: MutableLiveData<List<Headphones>> by lazy {
        MutableLiveData<List<Headphones>>()
    }

    init {
        getAllHeadphones()
    }

    fun getAllHeadphones() {
        viewModelScope.launch {
            val headphones = headphonesRepository.getAllItems()
            headphonesLiveData.value = headphones
        }
    }
}