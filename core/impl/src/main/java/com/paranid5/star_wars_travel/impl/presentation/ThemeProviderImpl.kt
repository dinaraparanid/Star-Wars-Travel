package com.paranid5.star_wars_travel.impl.presentation

import com.paranid5.star_wars_travel.core.common.presentation.Theme
import com.paranid5.star_wars_travel.core.common.presentation.ThemeProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ThemeProviderImpl : ThemeProvider {
    private val _themeState by lazy {
        MutableStateFlow(Theme.DARK)
    }

    override val themeState by lazy {
        _themeState.asStateFlow()
    }

    override fun setTheme(theme: Theme) =
        _themeState.update { theme }
}