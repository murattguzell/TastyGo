package com.muratguzel.tastygo.presentation.splash.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.muratguzel.tastygo.R
import com.muratguzel.tastygo.presentation.common.components.LottieLoader
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // LottieLoader component’ini burada çağırıyoruz
        LottieLoader(
            resId = R.raw.hamburger,
            modifier = Modifier.weight(1f)
        )
    }
    LaunchedEffect(Unit) {
        delay(3000)
        onTimeout()
    }

}