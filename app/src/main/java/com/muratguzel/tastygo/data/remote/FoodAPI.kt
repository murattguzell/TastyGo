package com.muratguzel.tastygo.data.remote

import com.muratguzel.tastygo.data.remote.dto.food.FoodListResponseDTO
import retrofit2.http.GET
import retrofit2.http.Header
interface FoodAPI {

    //tumYemekleriGetir.php
    @GET("tumYemekleriGetir.php")
    suspend fun getFood(): FoodListResponseDTO
}