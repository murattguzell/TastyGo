package com.muratguzel.tastygo.presentation.common.components

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.graphics.Shape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import coil.compose.AsyncImage
import coil.request.ImageRequest
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import com.muratguzel.tastygo.R

@Composable
fun NetworkImage(
    url: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(0),
    contentScale: ContentScale = ContentScale.Crop,
    crossfade: Boolean = true,
    placeholderRes: Int = R.drawable.food_placeholder,
    errorRes: Int = R.drawable.food_errorres,
    showLoading: Boolean = false,
) {
    val context = LocalContext.current
    var isLoading by remember { mutableStateOf(showLoading) }

    val request = ImageRequest.Builder(context)
        .data(url)
        .crossfade(crossfade)
        .placeholder(placeholderRes)
        .error(errorRes)
        .build()

    Box(modifier = modifier.clip(shape)) {
        AsyncImage(
            model = request,
            contentDescription = contentDescription,
            contentScale = contentScale,
            modifier = Modifier
                .matchParentSize()
                .clip(shape),
            onLoading = { isLoading = true },
            onSuccess = { isLoading = false },
            onError   = { isLoading = false }
        )

        if (showLoading && isLoading) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(Color.Black.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color.White)
            }
        }
    }
}