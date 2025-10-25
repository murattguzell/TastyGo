package com.muratguzel.tastygo.presentation.common.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Motorcycle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import kotlinx.coroutines.launch
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FoodItem(
    title: String,
    price: String,
    imageUrl: String,
    isFavorite: Boolean =false,
    cardOnClick: () -> Unit,
    onAddClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(260.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        onClick = cardOnClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.SpaceEvenly, // bölümleri eşit aralıklı dağıt
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 1) Görsel alanı
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
            ) {
                NetworkImage(
                    url = imageUrl,
                    contentDescription = "Yemek görseli",
                    modifier = Modifier.fillMaxSize(),
                    showLoading = true,
                )
                IconButton(
                    onClick = {
                        onFavoriteClick()
                    },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .size(30.dp)
                ) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = "Favori",
                        tint = if (isFavorite) MaterialTheme.colorScheme.primary else Color.Black,
                    )
                }
            }
            Spacer(Modifier.height(12.dp))

            // 2) Başlık
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()

            )
            Spacer(Modifier.height(12.dp))

            // 3) Teslimat satırı
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.Motorcycle,
                    contentDescription = "Ücretsiz teslimat",
                    modifier = Modifier.size(16.dp)
                )
                Spacer(Modifier.width(6.dp))
                Text(
                    text = "Ücretsiz teslimat",
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 12.sp,
                    color = Color.Black
                )
            }

            Spacer(Modifier.height(12.dp))

            // 4) Fiyat + ekle butonu
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "₺ ${price}",
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 18.sp,

                    )

                // "+" butonu: ripple + bounce (zıplama) animasyonu + haptic
                val interaction = remember { MutableInteractionSource() }
                val haptics = LocalHapticFeedback.current
                val scope = rememberCoroutineScope()
                val scale = remember { Animatable(1f) }

                FilledIconButton(
                    onClick = {
                        haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                        // Zıplama animasyonu: önce küçül, sonra geri büyü
                        scope.launch {
                            scale.animateTo(
                                targetValue = 0.90f,
                                animationSpec = spring(
                                    dampingRatio = 0.35f,
                                    stiffness = Spring.StiffnessHigh
                                )
                            )
                            scale.animateTo(
                                targetValue = 1f,
                                animationSpec = spring(
                                    dampingRatio = 0.30f,
                                    stiffness = Spring.StiffnessMedium
                                )
                            )
                        }
                        onAddClick()
                    },
                    modifier = Modifier
                        .size(40.dp)
                        .graphicsLayer(scaleX = scale.value, scaleY = scale.value),
                    interactionSource = interaction,
                    shape = RoundedCornerShape(8.dp),
                    colors = IconButtonDefaults.filledIconButtonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Icon(Icons.Filled.Add, contentDescription = "Sepete ekle")
                }
            }
        }
    }
}
