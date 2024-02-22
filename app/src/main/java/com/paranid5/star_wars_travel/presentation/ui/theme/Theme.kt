package com.paranid5.star_wars_travel.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LocalRippleConfiguration
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import com.paranid5.star_wars_travel.resources.ui.*
import org.koin.compose.KoinContext

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

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
}

val LocalAppColors = staticCompositionLocalOf { AppColors() }

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun StarWarsTravelTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val appColors = AppColors(if (darkTheme) DarkColorScheme else LightColorScheme)

    KoinContext {
        MaterialTheme(
            colorScheme = appColors.colorScheme,
            typography = Typography,
            content = {
                CompositionLocalProvider(
                    LocalAppColors provides appColors,
                    LocalRippleConfiguration provides SWRippleConfinguration
                ) {
                    content()
                }
            }
        )
    }
}