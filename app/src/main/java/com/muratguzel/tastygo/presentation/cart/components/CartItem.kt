package com.muratguzel.tastygo.presentation.cart.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.muratguzel.tastygo.domain.model.Cart
import com.muratguzel.tastygo.presentation.common.components.NetworkImage


@Composable
fun CartItem(cart: Cart) {
    Card(
        modifier = Modifier
            .fillMaxWidth().height(120.dp)

        .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            NetworkImage(
                url = cart.foodImageName,
                contentDescription = cart.foodName,
                modifier = Modifier
                    .size(80.dp)
                    .padding(end = 8.dp)
            )

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = cart.foodName,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.Black)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Fiyat: ₺${cart.foodPrice}",
                    style = MaterialTheme.typography.bodyLarge.copy(color =Color.Black, fontSize = 16.sp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Adet: ${cart.orderQuantity}",
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.Black, fontSize =14.sp)
                )
            }
            Column (modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.End){
                IconButton(
                    onClick = { /* TODO: Handle delete action */ },
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                val totalPrice = (cart.foodPrice.toDoubleOrNull() ?: 0.0) * (cart.orderQuantity.toIntOrNull() ?: 0)
                Text(
                    text = "₺${"%.2f".format(totalPrice)}",
                    style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold))
            }

        }
    }
}