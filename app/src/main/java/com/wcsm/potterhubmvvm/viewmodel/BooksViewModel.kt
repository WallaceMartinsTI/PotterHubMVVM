package com.wcsm.potterhubmvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wcsm.potterhubmvvm.api.RetrofitService
import com.wcsm.potterhubmvvm.model.Book
import kotlinx.coroutines.launch

class BooksViewModel : ViewModel() {
    private val potterAPI by lazy { RetrofitService.getPotterAPI() }

    val books = MutableLiveData<List<Book>>()

    fun getBooks() {
        viewModelScope.launch {
            try {
                val response = potterAPI.getBooks()
                if(response.isSuccessful && response.body() != null) {
                    books.postValue(response.body())
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}