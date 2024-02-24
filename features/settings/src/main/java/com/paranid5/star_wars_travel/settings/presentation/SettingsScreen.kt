package com.paranid5.star_wars_travel.settings.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.paranid5.star_wars_travel.resources.ui.LocalAppColors

@Composable
fun SettingsScreen(
    settingsViewModel: SettingsViewModel,
    modifier: Modifier = Modifier
) = Column(modifier) {
    ThemeMenu(
        viewModel = settingsViewModel,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp, bottom = 4.dp)
    )

    AlphaSeparator()
}

@Composable
private fun AlphaSeparator() {
    val colors = LocalAppColors.current

    Spacer(
        Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(colors.transparentUtility)
    )
}