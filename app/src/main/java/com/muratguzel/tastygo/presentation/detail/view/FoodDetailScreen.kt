package com.muratguzel.tastygo.presentation.detail.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.muratguzel.tastygo.domain.model.Food
import com.muratguzel.tastygo.presentation.common.components.NetworkImage
import com.muratguzel.tastygo.presentation.ui.theme.Orange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodDetailScreen(
    modifier: Modifier = Modifier,
    food: Food
) {
    var quantity by rememberSaveable { mutableStateOf(1) }
    val unitPrice = food.foodPrice.toInt()
    val totalPrice = unitPrice * quantity

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
                    IconButton(onClick = { /* geri */ }) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Geri",
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* favori */ }) {
                        Icon(
                            Icons.Outlined.FavoriteBorder,
                            contentDescription = "Favori",
                            tint = Color.White
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

            // ⭐ Rating
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                repeat(5) { index ->
                    val filled = index < 4
                    Icon(
                        imageVector = if (filled) Icons.Filled.Star else Icons.Outlined.Star,
                        contentDescription = null,
                        tint = Orange
                    )
                }
            }

            Spacer(Modifier.weight(1f))

            // 🍽 Görsel
            NetworkImage(
                url = food.foodImageName,
                contentDescription = "Yemek resmi",
                modifier = Modifier
                    .width(320.dp)
                    .height(300.dp)
                    .align(alignment = Alignment.CenterHorizontally)
                    .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
                showLoading = true,
            )


            // 💰 Fiyat
            Text(
                text = "₺ ${food.foodPrice}",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Orange,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(Modifier.height(12.dp))

            // 🍛 Başlık
            Text(
                text = food.foodName,
                fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(Modifier.height(16.dp))

            // ➕➖ Adet
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

            // Etiketler
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

@Composable
fun RoundButton(symbol: String, onClick: () -> Unit) {
    Surface(
        color = Orange,
        shape = RoundedCornerShape(12.dp),
        onClick = onClick
    ) {
        Box(
            modifier = Modifier
                .size(56.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                symbol,
                fontSize = 24.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun InfoChip(label: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xFFF1F1F1))
            .padding(horizontal = 14.dp, vertical = 8.dp),
    ) {
        Text(text = label, color = Color.DarkGray, fontSize = 13.sp)
    }
}

@Composable
fun BottomBar(total: Int) {
    Surface(shadowElevation = 8.dp) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .navigationBarsPadding(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "₺ $total",
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )
            Spacer(Modifier.weight(1f))
            Button(
                onClick = { /* sepete ekle */ },
                colors = ButtonDefaults.buttonColors(containerColor = Orange),
                shape = RoundedCornerShape(16.dp),
                contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp)
            ) {
                Text("Sepete Ekle", fontSize = 17.sp, color = Color.White)
            }
        }
    }
}
