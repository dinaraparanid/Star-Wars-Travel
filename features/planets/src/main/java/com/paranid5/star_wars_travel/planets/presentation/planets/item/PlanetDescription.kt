package com.paranid5.star_wars_travel.planets.presentation.planets.item

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.paranid5.star_wars_travel.impl.presentation.PlanetUiState
import com.paranid5.star_wars_travel.impl.presentation.mainRegion
import com.paranid5.star_wars_travel.resources.R

@Composable
fun PlanetDescription(planet: PlanetUiState, modifier: Modifier = Modifier) =
    Column(modifier) {
        PlanetTitle(planet.title)

        Spacer(Modifier.height(4.dp))

        PlanetInfoLabel(
            info = planet.mainRegion
                ?: stringResource(R.string.secret_location),
        )

        Spacer(Modifier.height(2.dp))

        PlanetInfoLabel(
            info = stringResource(
                R.string.climate_label,
                planet.physicalInformation.climate
            ),
        )

        Spacer(Modifier.height(2.dp))

        PlanetInfoLabel(
            info = stringResource(
                R.string.gravity_label,
                planet.physicalInformation.gravity
            ),
        )

        Spacer(Modifier.height(2.dp))

        PlanetInfoLabel(
            info = stringResource(
                R.string.population_label,
                populationLabel(planet)
            ),
        )
    }

@Composable
private fun populationLabel(planet: PlanetUiState) =
    when (val pop = planet.societalInformation.population) {
        0L -> stringResource(R.string.unknown)
        else -> pop.toString()
    }