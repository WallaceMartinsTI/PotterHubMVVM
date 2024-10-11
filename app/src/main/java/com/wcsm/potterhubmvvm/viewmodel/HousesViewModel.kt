package com.wcsm.potterhubmvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wcsm.potterhubmvvm.api.RetrofitService
import com.wcsm.potterhubmvvm.model.House
import kotlinx.coroutines.launch

class HousesViewModel : ViewModel() {
    private val potterAPI by lazy { RetrofitService.getPotterAPI() }

    val houses = MutableLiveData<List<House>>()

    fun getHouses() {
        viewModelScope.launch {
            try {
                val response = potterAPI.getHouses()
                if(response.isSuccessful && response.body() != null) {
                    houses.postValue(response.body())
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}