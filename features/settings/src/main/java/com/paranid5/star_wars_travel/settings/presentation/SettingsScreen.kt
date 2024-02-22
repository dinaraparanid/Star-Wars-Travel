package com.paranid5.star_wars_travel.settings.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.paranid5.star_wars_travel.resources.ui.StarWarsYellow

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) =
    Text(
        text = "Settings Screen",
        modifier = modifier,
        color = StarWarsYellow
    )