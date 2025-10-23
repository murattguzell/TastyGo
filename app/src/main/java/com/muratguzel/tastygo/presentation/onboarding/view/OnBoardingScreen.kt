package com.muratguzel.tastygo.presentation.onboarding.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.muratguzel.tastygo.presentation.onboarding.components.PageIndicators
import com.muratguzel.tastygo.presentation.onboarding.components.OnboardingActions
import com.muratguzel.tastygo.presentation.onboarding.model.listData
import com.muratguzel.tastygo.presentation.ui.theme.Grey
import kotlinx.coroutines.launch
import com.muratguzel.tastygo.presentation.onboarding.components.OnboardingPageView

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(onFinish: () -> Unit) {

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
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(0.6f)
        ) { page ->
            OnboardingPageView(listData[page].resId, listData[page].title, listData[page].desc)
        }
        PageIndicators(
            pageCount = listData.size,
            selectedIndex = selectedPage,
            activeColor = MaterialTheme.colorScheme.primary,
            inactiveColor = Grey
        )

        OnboardingActions(
            isLastPage = selectedPage == listData.size - 1,
            onSkip = { scope.launch { pagerState.animateScrollToPage(listData.size - 1) } },
            onNext = {
                scope.launch {
                    pagerState.animateScrollToPage(
                        (selectedPage + 1).coerceAtMost(
                            listData.size - 1
                        )
                    )
                }
            },
            onFinish = onFinish
        )
    }
}