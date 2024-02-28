package com.paranid5.star_wars_travel.planets.presentation.planet.interest

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.paranid5.star_wars_travel.data.ktor.wookiepedia.loadInterestCover
import com.paranid5.star_wars_travel.impl.presentation.Interest
import com.paranid5.star_wars_travel.impl.presentation.PlanetUiState
import com.paranid5.star_wars_travel.navigation.component.PlanetComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
internal fun InterestList(
    planet: PlanetUiState,
    planetComponent: PlanetComponent,
    modifier: Modifier = Modifier
) {
    var loadedPlanet by remember(planet) {
        mutableStateOf(planet)
    }

    LaunchedEffect(Unit) {
        val interests = withContext(Dispatchers.IO) {
            planet
                .physicalInformation
                .interest
                .map { Interest(it.value, loadInterestCover(it.value)) }
        }

        launch(Dispatchers.IO) { planetComponent.updateInterestsAsync(interests) }

        loadedPlanet = planet.copy(
            physicalInformation = planet.physicalInformation.copy(interest = interests)
        )
    }

    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(end = 16.dp)
    ) {
        items(loadedPlanet.physicalInformation.interest) {
            Row {
                InterestItem(
                    interest = it,
                    modifier = Modifier.width(100.dp)
                )

                Spacer(Modifier.width(8.dp))
            }
        }
    }
}