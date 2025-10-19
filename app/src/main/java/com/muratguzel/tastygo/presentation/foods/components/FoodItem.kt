package com.muratguzel.tastygo.presentation.foods.components


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.muratguzel.tastygo.presentation.common.components.NetworkImage
import com.muratguzel.tastygo.presentation.ui.theme.Orange
@Composable
fun FoodItem(
    title: String,
    price: String,
    imageUrl: String,
    cardOnClick:()-> Unit,
    onAddClick: () -> Unit
) {
    // Kartın oranı sabit: genişliğe göre yükseklik aynı kalır (tüm cihazlarda tutarlı)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(0.75f), // 0.7 çok basık kalıyorsa 0.9-1.0 iyi sonuç verir
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        onClick = cardOnClick
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // ÜST: Görsel alanı – sabit 160dp yerine oranla
            NetworkImage(
                url = imageUrl,
                contentDescription = "Yemek resmi",
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(60f)
                    .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
                showLoading = true,
            )

            Spacer(Modifier.height(8.dp))

            // ORTA: Başlık – 2 satıra sabitle, alt bara çarpmaması için min yükseklik ver
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontSize = 18.sp,
                maxLines = 2,
                overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .heightIn(min = 44.dp) // 2 satır için güvenli minimum
            )

            // Boşluk: Alt bar’ı her zaman en alta iter
            Spacer(Modifier.weight(1f))

            // ALT BAR: sabit yükseklik + iç boşluk
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = price,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Orange
                )

                FilledTonalButton(
                    onClick = onAddClick,
                    modifier = Modifier.height(40.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Orange)
                ) {
                    Text("+")
                }
            }
        }

    }
}