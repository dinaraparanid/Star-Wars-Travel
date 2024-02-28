package com.paranid5.star_wars_travel.presentation.ui.theme

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LocalRippleConfiguration
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.paranid5.star_wars_travel.core.common.presentation.Theme
import com.paranid5.star_wars_travel.resources.ui.AppColors
import com.paranid5.star_wars_travel.resources.ui.DarkColorScheme
import com.paranid5.star_wars_travel.resources.ui.LightColorScheme
import com.paranid5.star_wars_travel.resources.ui.LocalAppColors
import com.paranid5.star_wars_travel.resources.ui.SWRippleConfinguration
import org.koin.compose.KoinContext

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun StarWarsTravelTheme(
    theme: Theme,
    content: @Composable () -> Unit
) {
    val appColors = AppColors(
        when (theme) {
            Theme.LIGHT -> LightColorScheme
            Theme.DARK -> DarkColorScheme
        }
    )

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