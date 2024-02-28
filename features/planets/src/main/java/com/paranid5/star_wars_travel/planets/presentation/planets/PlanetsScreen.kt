package com.paranid5.star_wars_travel.planets.presentation.planets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.paranid5.star_wars_travel.navigation.component.planets.PlanetsComponent
import com.paranid5.star_wars_travel.planets.presentation.planets.region.RegionSelectors

@Composable
fun PlanetsScreen(
    planetsComponent: PlanetsComponent,
    modifier: Modifier = Modifier
) = Column(modifier) {
    PlanetsWelcomeLabel(
        Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    )

    Spacer(Modifier.height(16.dp))

    PlanetsSearchBar(
        planetsComponent = planetsComponent,
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(Modifier.height(16.dp))

    RegionSelectors(planetsComponent = planetsComponent)

    Spacer(Modifier.height(16.dp))

    PlanetList(
        planetsComponent = planetsComponent,
        modifier = Modifier.fillMaxWidth()
    )
}