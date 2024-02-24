package com.paranid5.star_wars_travel.planets.presentation.planets.region

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.paranid5.star_wars_travel.planets.presentation.planets.PlanetsViewModel

@Composable
fun RegionSelectors(
    viewModel: PlanetsViewModel,
    modifier: Modifier = Modifier
) {
    val regions = viewModel
        .regionsFlow
        .collectAsLazyPagingItems()

    val selectedRegs by viewModel
        .selectedRegionsState
        .collectAsState()

    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(regions.itemCount + 1) { i ->
            when (i) {
                0 -> RegionSelector(
                    region = null,
                    isSelected = null in selectedRegs,
                    viewModel = viewModel
                )

                else -> {
                    val (region, isSelected) = regions[i - 1]!!

                    RegionSelector(
                        region = region,
                        isSelected = isSelected,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}