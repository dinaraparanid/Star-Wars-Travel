package com.paranid5.star_wars_travel.planets.presentation.planets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.paranid5.star_wars_travel.planets.presentation.planets.region.RegionSelectors

@Composable
fun PlanetsScreen(
    planetsViewModel: PlanetsViewModel,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        PlanetsWelcomeLabel(
            Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )

        Spacer(Modifier.height(16.dp))

        PlanetsSearchBar(
            viewModel = planetsViewModel,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        RegionSelectors(viewModel = planetsViewModel)

        Spacer(Modifier.height(16.dp))

        PlanetList(
            viewModel = planetsViewModel,
            modifier = Modifier.fillMaxWidth()
        )
    }
}