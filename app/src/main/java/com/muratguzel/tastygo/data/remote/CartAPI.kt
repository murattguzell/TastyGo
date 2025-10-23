package com.muratguzel.tastygo.data.remote

import com.muratguzel.tastygo.data.remote.dto.cart.AddToCartRequest
import com.muratguzel.tastygo.data.remote.dto.cart.AddToCartResponse
import com.muratguzel.tastygo.data.remote.dto.cart.CartListResponseDTO
import com.muratguzel.tastygo.data.remote.dto.cart.DeleteFromCartRequest
import com.muratguzel.tastygo.data.remote.dto.cart.DeleteFromCartResponse
import com.muratguzel.tastygo.data.remote.dto.cart.GetCartItemsRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CartApi {

    @POST("sepeteYemekEkle.php")
    suspend fun addToCart(
        @Body request: AddToCartRequest
    ):AddToCartResponse

    @GET("sepettekiYemekleriGetir.php")
    suspend fun getCartItems(
        @Body request: GetCartItemsRequest
    ): CartListResponseDTO

    @POST("sepettenYemekSil.php")
    suspend fun deleteFromCart(
        @Body request: DeleteFromCartRequest
    ): DeleteFromCartResponse
}