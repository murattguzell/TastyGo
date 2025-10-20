package com.muratguzel.tastygo.presentation.navigation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.muratguzel.tastygo.presentation.navigation.BottomNavItem
import com.muratguzel.tastygo.presentation.ui.theme.Orange


@Composable
fun BottomBar(navController: NavController) {
    val items = listOf(
        BottomNavItem.FoodListScreen,
        BottomNavItem.Favorites,
        BottomNavItem.Cart,
        BottomNavItem.Profile
    )

    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry.value?.destination

    // renkler
    val selectedColor = Orange
    val unselectedColor = Color(0xFF9CA3AF)
    val barBg = Color.White

    Surface(color = barBg, tonalElevation = 0.dp) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 56.dp)
                .navigationBarsPadding(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            items.forEach { item ->
                val isSelected =
                    currentDestination?.hierarchy?.any { it.route == item.route } == true
                val color = if (isSelected) selectedColor else unselectedColor

                Column(
                    modifier = Modifier
                        .clickable {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                        .padding(top = 4.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                        tint = color
                    )
                    Text(
                        text = item.title,
                        color = color,
                        fontSize = 11.sp
                    )
                }
            }
        }
    }
}