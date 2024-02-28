package com.paranid5.star_wars_travel.planets.presentation.planets.region

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.paranid5.star_wars_travel.navigation.component.PlanetsComponent

@Composable
fun RegionSelectors(
    planetsComponent: PlanetsComponent,
    modifier: Modifier = Modifier
) {
    val regions = planetsComponent
        .regionsFlow
        .collectAsLazyPagingItems()

    val shownRegions by remember {
        derivedStateOf { regions.itemSnapshotList.distinct() }
    }

    val selectedRegs by planetsComponent
        .selectedRegionsState
        .collectAsState()

    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(shownRegions.size + 1) { i ->
            when (i) {
                0 -> RegionSelector(
                    region = null,
                    isSelected = null in selectedRegs,
                    planetsComponent = planetsComponent
                )

                else -> {
                    val (region, isSelected) = shownRegions[i - 1]!!

                    RegionSelector(
                        region = region,
                        isSelected = isSelected,
                        planetsComponent = planetsComponent
                    )
                }
            }
        }
    }
}