package com.paranid5.star_wars_travel.planets.presentation.planets.item

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.paranid5.star_wars_travel.impl.presentation.PlanetUiState

@Composable
internal fun PlanetItem(
    planet: PlanetUiState,
    modifier: Modifier = Modifier
) = Row(modifier) {
    PlanetCover(
        planet = planet,
        modifier = Modifier.align(Alignment.CenterVertically)
    )

    Spacer(Modifier.width(16.dp))

    PlanetDescription(
        planet = planet,
        modifier = Modifier.weight(1F)
    )
}
