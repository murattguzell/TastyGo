package com.muratguzel.tastygo.data.remote

import com.muratguzel.tastygo.data.remote.dto.cart.AddToCartResponseDTO
import com.muratguzel.tastygo.data.remote.dto.cart.CartListResponseDTO
import com.muratguzel.tastygo.data.remote.dto.cart.DeleteFromCartResponseDTO
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface CartAPI {

    @FormUrlEncoded
    @POST("sepeteYemekEkle.php")
    suspend fun addToCart(
        @Field("yemek_adi") foodName: String,
        @Field("yemek_resim_adi") foodImageName: String,
        @Field("yemek_fiyat") foodPrice: Int,
        @Field("yemek_siparis_adet") orderQuantity: Int,
        @Field("kullanici_adi") userName: String
    ): AddToCartResponseDTO
    @FormUrlEncoded
    @POST("sepettekiYemekleriGetir.php")
    suspend fun getCartItems(
        @Field("kullanici_adi") userName: String
    ): CartListResponseDTO

    @FormUrlEncoded
    @POST("sepettenYemekSil.php")
    suspend fun deleteFromCart(
        @Field("kullanici_adi")
        userName: String,
        @Field("sepet_yemek_id")
        cartItemId: Int
    ): DeleteFromCartResponseDTO
}