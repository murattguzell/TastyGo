package com.muratguzel.tastygo.data.entity

import androidx.annotation.RawRes
import com.muratguzel.tastygo.R


class OnBoardingData(@RawRes val resId: Int, val title: String, val desc: String)

val listData = listOf(
    OnBoardingData(
        R.raw.phone,
        "Favori Yemeklerin Parmaklarınızın Ucunda!",
        "Restoranlar arasında dolaşmadan, birkaç dokunuşla dilediğin yemeği hemen sipariş et. Açlığı bekletme, lezzeti keşfet!",
    ),
    OnBoardingData(
        R.raw.man,
        "Lezzet Dünyasında Kaybolmayın!",
        "Yüzlerce seçenek arasında hangisini seçeceğine karar veremiyor musun? Tavsiyelerimiz ve popüler menülerle seçim yapmayı kolaylaştır.",
    ),
    OnBoardingData(
        R.raw.deneme,
        "Kapına Gelen Sıcak Lezzetler!",
        "Hızlı, güvenilir ve sıcak teslimat garantisiyle siparişin yola çıktı! Lezzet dolu bir yolculuğa hazır olun.",
    ),
)