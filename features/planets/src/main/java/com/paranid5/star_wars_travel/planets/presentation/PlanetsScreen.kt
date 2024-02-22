package com.paranid5.star_wars_travel.planets.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.paranid5.star_wars_travel.resources.ui.StarWarsYellow

@Composable
fun PlanetsScreen(modifier: Modifier = Modifier) =
    Text(
        text = "Planets screen",
        color = StarWarsYellow,
        modifier = modifier
    )