package com.muratguzel.tastygo.data.remote.dto.cart

import com.google.gson.annotations.SerializedName

data class DeleteFromCartRequestDTO(
    @SerializedName("kullanici_adi")
    val userName: String,
    @SerializedName("sepet_yemek_id")
    val cartItemId: Int
)