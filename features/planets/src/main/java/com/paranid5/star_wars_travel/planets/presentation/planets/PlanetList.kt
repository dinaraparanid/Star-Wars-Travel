package com.paranid5.star_wars_travel.planets.presentation.planets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.paranid5.star_wars_travel.navigation.component.PlanetsComponent
import com.paranid5.star_wars_travel.navigation.composition_locals.LocalNavigator
import com.paranid5.star_wars_travel.planets.presentation.planets.item.PlanetItem

@Composable
fun PlanetList(
    planetsComponent: PlanetsComponent,
    modifier: Modifier = Modifier
) {
    val navigator = LocalNavigator.current!!

    val planets = planetsComponent
        .planetsFlow
        .collectAsLazyPagingItems()

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(planets.itemCount, key = { it }) {
            val planet = planets[it]!!

            PlanetItem(
                planet = planet,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navigator.navigateToPlanet(planet) }
            )

            Spacer(Modifier.height(8.dp))
        }
    }
}