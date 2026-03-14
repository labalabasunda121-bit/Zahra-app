package com.zahra.space.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = Gold60,
    onPrimary = Color.White,
    secondary = Pink60,
    onSecondary = Color.White,
    tertiary = Cream,
    onTertiary = SoftBrown,
    background = Cream,
    onBackground = SoftBrown,
    surface = Color.White,
    onSurface = SoftBrown,
    error = Color(0xFFBA1A1A),
    onError = Color.White
)

private val DarkColorScheme = darkColorScheme(
    primary = Gold80,
    onPrimary = SoftBrown,
    secondary = Pink80,
    onSecondary = SoftBrown,
    tertiary = DarkSurface,
    onTertiary = Color.White,
    background = DarkBackground,
    onBackground = Color.White,
    surface = DarkSurface,
    onSurface = Color.White,
    error = Color(0xFFFFB4AB),
    onError = SoftBrown
)

@Composable
fun ZahraSpaceTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
