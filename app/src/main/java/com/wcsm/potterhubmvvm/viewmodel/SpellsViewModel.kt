package com.wcsm.potterhubmvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wcsm.potterhubmvvm.api.RetrofitService
import com.wcsm.potterhubmvvm.model.Spell
import kotlinx.coroutines.launch

class SpellsViewModel : ViewModel() {
    private val potterAPI by lazy { RetrofitService.getPotterAPI() }

    val spells = MutableLiveData<List<Spell>>()

    fun getSpells() {
        viewModelScope.launch {
            try {
                val response = potterAPI.getSpells()
                if(response.isSuccessful && response.body() != null) {
                    spells.postValue(response.body())
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}