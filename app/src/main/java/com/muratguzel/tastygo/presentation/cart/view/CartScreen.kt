package com.muratguzel.tastygo.presentation.cart.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.NumberFormat
import java.util.Locale
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.muratguzel.tastygo.domain.model.cart.DeleteFromCartRequest
import com.muratguzel.tastygo.presentation.cart.components.CartItem
import com.muratguzel.tastygo.presentation.cart.viewmodel.CartViewModel

@Composable
fun CartScreen(
    modifier: Modifier = Modifier,
    cartViewModel: CartViewModel = hiltViewModel(),
) {
    val cartState by cartViewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        cartViewModel.loadCart("murat")
    }

    // Toplam tutarı hesapla
    val total = remember(cartState.food) {
        cartState.food.sumOf { (it.foodPrice.toDoubleOrNull() ?: 0.0) * (it.orderQuantity.toIntOrNull() ?: 0) }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Color.Transparent,
        bottomBar = {
            CartSummaryBar(
                shippingFee = 0.0,
                total = total,
                onConfirm = {
                    // Sepeti onayla aksiyonu: şimdilik TODO, snack/route ekleyebilirsin
                }
            )
        }
    ) { innerPadding ->
        when {
            cartState.isLoading -> {
                LoadingView()
            }
            cartState.error.isNotEmpty() -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Şu anda sepette ürün yok", style = MaterialTheme.typography.bodyLarge)
                }
            }
            else -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentPadding = PaddingValues(bottom = 150.dp)
                ) {
                    items(cartState.food) { item ->
                        CartItem(
                            item,
                            onDeleteClick = {
                                cartViewModel.deleteFromCart(
                                    DeleteFromCartRequest("murat", item.cartItemId)
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}
@Composable
fun LoadingView() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun CartSummaryBar(
    shippingFee: Double,
    total: Double,
    onConfirm: () -> Unit
) {
    Surface(tonalElevation = 4.dp, shadowElevation = 8.dp) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            // Gönderim ücreti
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Gönderim Ücreti",
                    style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
                )
                Text(
                    text = "₺$shippingFee",
                    style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            // Toplam
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Toplam:",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                )
                Text(
                    text = "₺$total",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = onConfirm,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text(
                    text = "SEPETİ ONAYLA",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
        }
    }
}

