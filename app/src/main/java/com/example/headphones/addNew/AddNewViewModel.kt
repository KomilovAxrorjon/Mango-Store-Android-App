package com.example.headphones.addNew

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.headphones.data.HeadphonesRepository
import com.example.headphones.data.dataClasses.NewHeadphones
import com.example.headphones.data.network.NewResponse
import kotlinx.coroutines.launch

class AddNewViewModel(
    private val headphonesRepository: HeadphonesRepository
): ViewModel(){

    val insertRessponseLiveData: MutableLiveData<NewResponse> by lazy {
        MutableLiveData<NewResponse>()
    }

    fun SaveNewItem (headphones: NewHeadphones){
        viewModelScope.launch {
            try {
                val response = headphonesRepository.addNewItem(headphones)
                insertRessponseLiveData.value = response
                Log.d("Update_response", response.toString())
            } catch (ex: Exception){
                ex.printStackTrace()
            }
        }
    }

}