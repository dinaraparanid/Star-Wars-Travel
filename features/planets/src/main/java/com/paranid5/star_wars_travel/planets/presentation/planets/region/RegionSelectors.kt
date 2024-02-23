package com.paranid5.star_wars_travel.planets.presentation.planets.region

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.paranid5.star_wars_travel.planets.presentation.planets.PlanetsViewModel
import kotlinx.collections.immutable.persistentListOf

@Composable
fun RegionSelectors(
    viewModel: PlanetsViewModel,
    modifier: Modifier = Modifier
) {
    val regions by viewModel
        .regionsFlow
        .collectAsState(initial = persistentListOf())

    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(regions) { (region, isSelected) ->
            RegionSelector(
                region = region,
                isSelected = isSelected,
                viewModel = viewModel
            )

            Spacer(Modifier.width(4.dp))
        }
    }
}