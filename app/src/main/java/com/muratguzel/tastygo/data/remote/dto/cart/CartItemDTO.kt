package com.muratguzel.tastygo.data.remote.dto.cart

import com.google.gson.annotations.SerializedName

data class CartItemDTO(
    @SerializedName("kullanici_adi") val userName: String,
    @SerializedName("sepet_yemek_id") val cartItemId: String,
    @SerializedName("yemek_adi") val foodName: String,
    @SerializedName("yemek_fiyat") val foodPrice: String,
    @SerializedName("yemek_resim_adi") val foodImageName: String,
    @SerializedName("yemek_siparis_adet") val orderQuantity: String
)