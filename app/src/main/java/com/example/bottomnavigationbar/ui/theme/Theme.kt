package com.example.bottomnavigationbar.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val CustomDarkColorScheme = darkColorScheme(
    primary = Color(0xFF14C6A4),
    // Otros colores oscuros...
)

private val CustomLightColorScheme = lightColorScheme(
    primary = Color(0xFF14C6A4),
    // Otros colores claros...
)

@Composable
fun CustomBottomNavigationBarTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Otros parámetros requeridos...
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) CustomDarkColorScheme else CustomLightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}