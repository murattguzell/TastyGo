package com.muratguzel.tastygo.data.remote.dto.cart

import com.google.gson.annotations.SerializedName

data class AddToCartRequest(
    @SerializedName("yemek_adi") val foodName: String,
    @SerializedName("yemek_resim_adi") val foodImageName: String,
    @SerializedName("yemek_fiyat") val foodPrice: Int,
    @SerializedName("yemek_siparis_adet") val orderQuantity: Int,
    @SerializedName("kullanici_adi") val userName: String
)