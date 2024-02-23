package com.paranid5.star_wars_travel.planets.presentation.planets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.paranid5.star_wars_travel.planets.presentation.planets.item.PlanetItem

@Composable
fun PlanetList(
    viewModel: PlanetsViewModel,
    modifier: Modifier = Modifier
) {
    val planets = viewModel
        .planetsFlow
        .collectAsLazyPagingItems()

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(planets.itemCount, key = { it }) {
            PlanetItem(
                planet = planets[it]!!,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(8.dp))
        }
    }
}