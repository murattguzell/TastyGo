package com.muratguzel.tastygo.presentation.detail.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Composable
fun RoundButton(symbol: String, onClick: () -> Unit) {
    val haptics = LocalHapticFeedback.current
    val scope = rememberCoroutineScope()
    val scale = remember { Animatable(1f) }

    Surface(
        color = MaterialTheme.colorScheme.primary,
        shape = RoundedCornerShape(12.dp),
        onClick = {
            // haptic + bounce
            haptics.performHapticFeedback(HapticFeedbackType.LongPress)
            scope.launch {
                // küçül
                scale.animateTo(
                    targetValue = 0.90f,
                    animationSpec = spring(
                        dampingRatio = 0.35f,
                        stiffness = Spring.StiffnessHigh
                    )
                )
                // geri büyü
                scale.animateTo(
                    targetValue = 1f,
                    animationSpec = spring(
                        dampingRatio = 0.30f,
                        stiffness = Spring.StiffnessMedium
                    )
                )
            }
            onClick()
        },
        modifier = Modifier.graphicsLayer(
            scaleX = scale.value,
            scaleY = scale.value
        )
    ) {
        Box(
            modifier = Modifier.size(56.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = symbol,
                fontSize = 24.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}