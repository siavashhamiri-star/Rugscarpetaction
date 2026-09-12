package com.example.farshbazar.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
    primary = Burgundy40,
    onPrimary = Color.White,
    primaryContainer = Burgundy80,
    onPrimaryContainer = BurgundyDark,
    secondary = Gold40,
    onSecondary = Color.Black,
    secondaryContainer = Gold80,
    tertiary = Navy40,
    onTertiary = Color.White,
    background = CreamBackground,
    onBackground = Color(0xFF1C1B1F),
    surface = WarmCardSurface,
    onSurface = Color(0xFF1C1B1F)
)

private val DarkColorScheme = darkColorScheme(
    primary = Burgundy80,
    onPrimary = BurgundyDark,
    primaryContainer = Burgundy40,
    secondary = Gold80,
    onSecondary = Color.Black,
    background = DarkBackground,
    onBackground = Color(0xFFE6E1E5),
    surface = DarkSurface,
    onSurface = Color(0xFFE6E1E5)
)

@Composable
fun FarshBazarTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
