package com.paranid5.star_wars_travel.resources.ui

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80,
    background = Color.DarkGray,
    onBackground = Color.White
)

val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40,
    background = Color.LightGray,
    onBackground = Color.Black

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@JvmInline
value class AppColors(val colorScheme: ColorScheme = DarkColorScheme) {
    val primary
        get() = colorScheme.primary

    val secondary
        get() = colorScheme.secondary

    val background
        get() = colorScheme.background

    val onBackground
        get() = colorScheme.onBackground
}

val LocalAppColors = staticCompositionLocalOf { AppColors() }