package com.paranid5.star_wars_travel.planets.presentation.planets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.paranid5.star_wars_travel.planets.presentation.planets.item.PlanetItem
import kotlinx.collections.immutable.persistentListOf

@Composable
fun PlanetList(
    viewModel: PlanetsViewModel,
    modifier: Modifier = Modifier
) {
    val planets by viewModel
        .planetsFlow
        .collectAsState(initial = persistentListOf())

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(planets, key = { it }) {
            PlanetItem(
                planet = it,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(8.dp))
        }
    }
}