package com.paranid5.star_wars_travel.planets.presentation.planets.item

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
        PlanetTitle(
            planetTitle = planet.title,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(4.dp))

        PlanetInfoLabel(
            info = planet.mainRegion ?: stringResource(R.string.secret_location),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(2.dp))

        PlanetInfoLabel(
            info = stringResource(R.string.climate_label, planet.physicalInformation.climate),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(2.dp))

        PlanetInfoLabel(
            info = stringResource(R.string.gravity_label, planet.physicalInformation.gravity),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(2.dp))

        PlanetInfoLabel(
            info = stringResource(R.string.gravity_label, planet.physicalInformation.gravity),
            modifier = Modifier.fillMaxWidth()
        )

        PlanetInfoLabel(
            info = stringResource(R.string.gravity_label, planet.societalInformation.population),
            modifier = Modifier.fillMaxWidth()
        )

        PlanetInfoLabel(
            info = planet.description ?: stringResource(R.string.no_planet_description),
            modifier = Modifier.fillMaxWidth(),
            maxLines = 3
        )
    }