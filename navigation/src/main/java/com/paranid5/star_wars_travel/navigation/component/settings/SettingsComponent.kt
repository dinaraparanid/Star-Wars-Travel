package com.paranid5.star_wars_travel.navigation.component.settings

import com.paranid5.star_wars_travel.core.common.presentation.Theme
import kotlinx.coroutines.flow.StateFlow

interface SettingsComponent {
    val themeState: StateFlow<Theme>

    fun resetTheme()
}