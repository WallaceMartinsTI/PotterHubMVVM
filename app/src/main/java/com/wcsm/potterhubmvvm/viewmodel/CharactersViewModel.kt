package com.wcsm.potterhubmvvm.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wcsm.potterhubmvvm.api.RetrofitService
import com.wcsm.potterhubmvvm.model.Character
import kotlinx.coroutines.launch

class CharactersViewModel : ViewModel() {
    private val potterAPI by lazy { RetrofitService.getPotterAPI() }

    val characters = MutableLiveData<List<Character>>()

    fun getCharacters() {
        viewModelScope.launch {
            try {
                val response = potterAPI.getCharacters()
                if(response.isSuccessful && response.body() != null) {
                    characters.postValue(response.body())
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}