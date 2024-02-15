package com.example.bottomnavigationbar.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bottomnavigationbar.SucessfulOperationScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppScreens.SuccessfulOperationScreen.route) {
        composable(route = AppScreens.SuccessfulOperationScreen.route) {
            SucessfulOperationScreen(navController)
        }
    }
}