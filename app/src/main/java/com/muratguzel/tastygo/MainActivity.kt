package com.muratguzel.tastygo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.muratguzel.tastygo.ui.screens.OnBoardingScreen
import com.muratguzel.tastygo.ui.screens.SplashScreen
import com.muratguzel.tastygo.ui.theme.TastyGoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            var isSplashFinished by remember { mutableStateOf(false) }

            TastyGoTheme {

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    Box(modifier = Modifier.padding(innerPadding)) {
                        NavHost(navController = navController, startDestination = "splash_screen") {
                            composable("splash_screen") {
                                SplashScreen() {
                                    isSplashFinished = true
                                    navController.navigate("OnBoardingScreen")
                                }
                            }
                            composable("OnBoardingScreen") {
                                OnBoardingScreen()
                            }
                        }
                    }
                }
            }
        }
    }
}

