package com.wcsm.potterhubmvvm.api

import com.wcsm.potterhubmvvm.model.Book
import com.wcsm.potterhubmvvm.model.Character
import com.wcsm.potterhubmvvm.model.House
import com.wcsm.potterhubmvvm.model.Spell
import retrofit2.Response
import retrofit2.http.GET

interface PotterAPI {

    @GET("books")
    suspend fun getBooks(): Response<List<Book>>

    @GET("characters")
    suspend fun getCharacters(): Response<List<Character>>

    @GET("houses")
    suspend fun getHouses(): Response<List<House>>

    @GET("spells")
    suspend fun getSpells(): Response<List<Spell>>

}