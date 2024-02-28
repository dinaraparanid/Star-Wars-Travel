package com.paranid5.star_wars_travel.navigation.component.planets

import androidx.paging.PagingData
import com.paranid5.star_wars_travel.impl.presentation.PlanetUiState
import com.paranid5.star_wars_travel.impl.presentation.RegionUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface PlanetsComponent {
    val searchTextState: StateFlow<String>

    fun setSearchText(text: String)

    val selectedRegionsState: StateFlow<List<String?>>

    fun reselectRegion(region: String?)

    val planetsFlow: Flow<PagingData<PlanetUiState>>

    val regionsFlow: Flow<PagingData<RegionUiState>>
}