package com.example.bottomnavigationbar.navigation

sealed class AppScreens(val route: String) {
    object HomeScreen: AppScreens("homeScreen")
    object SuccessfulOperationScreen: AppScreens("successfullOperationScreen")
}