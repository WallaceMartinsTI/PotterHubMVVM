package com.wcsm.potterhubmvvm.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    private const val BASE_URL = "https://potterapi-fedeperin.vercel.app/pt/"

    fun getPotterAPI() : PotterAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PotterAPI::class.java)
    }
}