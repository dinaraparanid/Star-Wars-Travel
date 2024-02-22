package com.paranid5.star_wars_travel.about_app.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.paranid5.star_wars_travel.resources.ui.StarWarsYellow

@Composable
fun AboutAppScreen(modifier: Modifier = Modifier) =
    Text(
        text = "About App",
        modifier = modifier,
        color = StarWarsYellow
    )