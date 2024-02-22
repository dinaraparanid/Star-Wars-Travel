package com.paranid5.star_wars_travel.planets.presentation.planets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.paranid5.star_wars_travel.impl.presentation.PlanetUiState
import com.paranid5.star_wars_travel.planets.presentation.planets.item.PlanetItem
import kotlinx.collections.immutable.ImmutableList

@Composable
internal fun PlanetList(
    planets: ImmutableList<PlanetUiState>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(planets, key = { it }) {
            PlanetItem(
                planet = it,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}