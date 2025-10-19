package com.muratguzel.tastygo.presentation.onboarding.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.muratguzel.tastygo.presentation.ui.theme.Orange

@Composable
fun OnboardingActions(isLastPage: Boolean,
    onSkip: () -> Unit,
    onNext: () -> Unit,
    onFinish: () -> Unit,
    modifier: Modifier = Modifier,
    nextButtonColors: ButtonColors = ButtonDefaults.buttonColors(Orange),
    finishButtonColors: ButtonColors = ButtonDefaults.buttonColors(Orange)
) {
    if (!isLastPage) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            TextButton(
                onClick = onSkip,
                modifier = Modifier.height(56.dp)
            ) {
                Text(text = "Skip", style = TextStyle(color = Orange))
            }
            Button(
                onClick = onNext,
                modifier = Modifier
                    .height(56.dp),
                shape = RoundedCornerShape(20.dp),
                colors = nextButtonColors
            ) {
                Text(text = "Next")
            }
        }
    } else {
        Button(
            onClick = onFinish,
            modifier = modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(56.dp),
            colors = finishButtonColors,
            shape = RoundedCornerShape(10.dp),
        ) {
            Text(text = "Get Started")
        }
    }
}