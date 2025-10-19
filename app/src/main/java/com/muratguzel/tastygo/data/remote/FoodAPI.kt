package com.muratguzel.tastygo.data.remote

import com.muratguzel.tastygo.data.remote.dto.FoodDTO
import retrofit2.http.GET
import retrofit2.http.Header

interface FoodAPI {

    //tumYemekleriGetir.php
    @GET("tumYemekleriGetir.php")
    suspend fun getFood(): FoodDTO
}