package com.paranid5.star_wars_travel.navigation.component.settings

import com.arkivanov.decompose.ComponentContext
import com.paranid5.star_wars_travel.core.common.presentation.Theme
import com.paranid5.star_wars_travel.core.common.presentation.ThemeProvider

class SettingsComponentImpl(
    private val themeProvider: ThemeProvider,
    componentContext: ComponentContext
) : ComponentContext by componentContext, SettingsComponent {
    override val themeState by lazy {
        themeProvider.themeState
    }

    override fun resetTheme() =
        themeProvider.setTheme(alternativeTheme(themeState.value))
}

internal fun alternativeTheme(currentTheme: Theme) =
    when (currentTheme) {
        Theme.LIGHT -> Theme.DARK
        Theme.DARK -> Theme.LIGHT
    }