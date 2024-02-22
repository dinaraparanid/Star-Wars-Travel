package com.paranid5.star_wars_travel.planets.presentation.planets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import kotlinx.collections.immutable.persistentListOf

@Composable
fun PlanetsScreen(
    planetsViewModule: PlanetsViewModule,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(Unit) {
        planetsViewModule.fetchAndStorePlanets()
    }

    val planets by planetsViewModule
        .planetsFlow
        .collectAsState(initial = persistentListOf())

    PlanetList(
        planets = planets,
        modifier = modifier
    )
}