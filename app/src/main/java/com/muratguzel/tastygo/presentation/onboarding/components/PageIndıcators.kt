package com.muratguzel.tastygo.presentation.onboarding.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
 fun PageIndicators(
    pageCount: Int,
    selectedIndex: Int,
    activeColor: Color,
    inactiveColor: Color,
    modifier: Modifier = Modifier,
    itemWidthSelected: Dp = 20.dp,
    itemWidthUnselected: Dp = 10.dp,
    itemHeight: Dp = 10.dp,
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        repeat(pageCount) { i ->
            Box(
                modifier = Modifier
                    .padding(end = if (i == pageCount - 1) 0.dp else 5.dp)
                    .width(if (i == selectedIndex) itemWidthSelected else itemWidthUnselected)
                    .height(itemHeight)
                    .clip(RoundedCornerShape(10.dp))
                    .background(if (i == selectedIndex) activeColor else inactiveColor)
            )
        }
    }
}