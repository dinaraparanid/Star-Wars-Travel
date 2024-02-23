package com.paranid5.star_wars_travel.impl.use_case

import com.paranid5.star_wars_travel.impl.presentation.PlanetUiState
import com.paranid5.star_wars_travel.impl.presentation.mainRegion
import kotlinx.collections.immutable.toImmutableList

fun List<PlanetUiState>.filterByRegions(regions: List<String?>) =
    when {
        null in regions -> toImmutableList()
        else -> filter { it.mainRegion in regions }.toImmutableList()
    }

fun List<PlanetUiState>.filterBySearchQuery(searchText: String) =
    filter { searchText in it.title.lowercase() }.toImmutableList()