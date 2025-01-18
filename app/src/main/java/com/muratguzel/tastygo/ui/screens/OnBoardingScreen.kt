package com.muratguzel.tastygo.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.muratguzel.tastygo.data.entity.listData
import com.muratguzel.tastygo.ui.theme.Grey
import com.muratguzel.tastygo.ui.theme.Orange
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen() {

    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(initialPage = 0) {
        listData.size
    }
    val (selectedPage, setSelectedPage) = remember {
        mutableStateOf(0)
    }

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            setSelectedPage(page)
        }
    }
    Column(modifier = Modifier
        .background(Color.White)
        .fillMaxSize()) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(0.6f)
        ) { page ->
            val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(listData[page].resId))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                LottieAnimation(
                    composition,
                    /// looping the animation
                    iterations = LottieConstants.IterateForever,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    listData[page].title,
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center,
                )
                Box(modifier = Modifier.height(24.dp))
                Text(
                    listData[page].desc,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            for (i in listData.indices) {
                Box(
                    modifier = Modifier
                        .padding(end = if (i == listData.size - 1) 0.dp else 5.dp)
                        .width(if (i == selectedPage) 20.dp else 10.dp)
                        .height(10.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(
                            if (i == selectedPage) Orange else Grey
                        )
                )
            }
        }

        // only show if not last page
        if (selectedPage != listData.size - 1) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                TextButton(
                    onClick = {
                        scope.launch {
                            /// animate to last page
                            pagerState.animateScrollToPage(listData.size - 1)
                        }
                    },
                    modifier = Modifier.height(56.dp)
                ) {
                    Text(
                        text = "Skip", style = TextStyle(color = Orange)
                    )
                }

                Button(
                    onClick = {
                        scope.launch {

                            val nextPage = selectedPage + 1
                            pagerState.animateScrollToPage(nextPage)
                        }
                    },
                    modifier = Modifier
                        .height(56.dp)
                        .background(color = Orange),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(Orange)
                ) {
                    Text(text = "Next")
                }
            }
        }

        /// show only in last page
        if (selectedPage == listData.size - 1) {
            Button(
                onClick = {

                },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(Orange),
                shape = RoundedCornerShape(10.dp),
            ) {
                Text(text = "Get Started")
            }
        }
    }


}