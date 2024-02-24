package com.paranid5.star_wars_travel.settings.presentation

import androidx.lifecycle.ViewModel
import com.paranid5.star_wars_travel.core.common.presentation.Theme
import com.paranid5.star_wars_travel.core.common.presentation.ThemeProvider

class SettingsViewModel(private val themeProvider: ThemeProvider) : ViewModel() {
    val themeState by lazy {
        themeProvider.themeState
    }

    fun resetTheme() =
        themeProvider.setTheme(alternativeTheme(themeState.value))
}

internal fun alternativeTheme(currentTheme: Theme) =
    when (currentTheme) {
        Theme.LIGHT -> Theme.DARK
        Theme.DARK -> Theme.LIGHT
    }