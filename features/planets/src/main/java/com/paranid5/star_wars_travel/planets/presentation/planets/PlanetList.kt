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
import com.paranid5.star_wars_travel.navigation.component.planets.PlanetsComponent
import com.paranid5.star_wars_travel.navigation.composition_locals.LocalNavigator
import com.paranid5.star_wars_travel.planets.presentation.planets.item.PlanetItem

@Composable
internal fun PlanetList(
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
        items(planets.itemCount, key = { it }) { index ->
            planets[index]?.let {
                PlanetItem(
                    planet = it,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { navigator.navigateToPlanet(it) }
                )
            }

            Spacer(Modifier.height(8.dp))
        }
    }
}
