package com.muratguzel.tastygo.presentation.detail.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.StarHalf
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material.icons.rounded.StarHalf
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.muratguzel.tastygo.domain.model.Food
import com.muratguzel.tastygo.presentation.common.components.NetworkImage
import com.muratguzel.tastygo.presentation.detail.components.BottomBar
import com.muratguzel.tastygo.presentation.detail.components.InfoChip
import com.muratguzel.tastygo.presentation.detail.components.RoundButton
import com.muratguzel.tastygo.presentation.ui.theme.Orange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodDetailScreen(
    modifier: Modifier = Modifier,
    food: Food,
    onBackClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {
    var quantity by rememberSaveable { mutableStateOf(1) }
    val unitPrice = food.foodPrice.toInt()
    val totalPrice = unitPrice * quantity

    var isFavorite by remember { mutableStateOf(false) }

    Scaffold(
        containerColor = Color.Transparent,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Ürün Detayı",
                        color = Color.White,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick =  onBackClick) {
                        Icon(
                            Icons.Default.Close,
                            contentDescription = "Geri",
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {  isFavorite = !isFavorite
                        onFavoriteClick()}) {
                        Icon(
                            imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                            contentDescription = "Favori",
                            tint =  Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Orange)
            )
        },
        bottomBar = {
            BottomBar(total = totalPrice)
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize(),
        ) {
            Spacer(Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                val rating = 3.3f
                repeat(5) { i ->
                    val icon = when {
                        i + 1 <= rating.toInt() -> Icons.Filled.Star
                        i < rating && rating % 1f >= 0.25f -> Icons.Rounded.StarHalf
                        else -> Icons.Outlined.StarBorder
                    }
                    Icon(icon, contentDescription = null, tint = Orange)
                }
                }


            Spacer(Modifier.weight(1f))

            NetworkImage(
                url = food.foodImageName,
                contentDescription = "Yemek resmi",
                modifier = Modifier
                    .width(320.dp)
                    .height(300.dp)
                    .align(alignment = Alignment.CenterHorizontally),
                showLoading = true,
            )


            Text(
                text = "₺ ${food.foodPrice}",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Orange,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(Modifier.height(12.dp))

            Text(
                text = food.foodName,
                fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                RoundButton("-", onClick = { if (quantity > 1) quantity-- })
                Text(text = "$quantity", fontSize = 26.sp, fontWeight = FontWeight.Bold)
                RoundButton("+", onClick = { quantity++ })
            }

            Spacer(Modifier.weight(1f))

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth(),
            ) {
                InfoChip("25–35 dk")
                InfoChip("Ücretsiz Teslimat")
                InfoChip("İndirim %10")
            }

            Spacer(Modifier.weight(1f))
        }
    }
}





