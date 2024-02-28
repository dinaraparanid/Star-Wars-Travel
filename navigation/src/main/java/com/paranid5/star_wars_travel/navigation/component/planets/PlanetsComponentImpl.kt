package com.paranid5.star_wars_travel.navigation.component.planets

import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import androidx.paging.map
import com.arkivanov.decompose.ComponentContext
import com.paranid5.star_wars_travel.data.PlanetsRepository
import com.paranid5.star_wars_travel.impl.presentation.PlanetUiState
import com.paranid5.star_wars_travel.impl.presentation.RegionUiState
import com.paranid5.star_wars_travel.impl.presentation.mainRegion
import com.paranid5.star_wars_travel.navigation.component.componentCoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.update

class PlanetsComponentImpl(
    private val planetsRepository: PlanetsRepository,
    componentContext: ComponentContext
) : ComponentContext by componentContext, PlanetsComponent {
    private val _searchTextState by lazy {
        MutableStateFlow("")
    }

    override val searchTextState by lazy {
        _searchTextState.asStateFlow()
    }

    override fun setSearchText(text: String) =
        _searchTextState.update { text }

    private val _selectedRegionsState by lazy {
        MutableStateFlow(listOf<String?>(null))
    }

    override val selectedRegionsState by lazy {
        _selectedRegionsState.asStateFlow()
    }

    override fun reselectRegion(region: String?) =
        when (region) {
            null -> _selectedRegionsState.update { listOf(null) }
            else -> _selectedRegionsState.update {
                when {
                    region in it -> if (it.size == 1) listOf(null) else it - region
                    else -> it - null + region
                }
            }
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    private val planetsUiFlow by lazy {
        planetsRepository
            .planetsPagedFlow
            .mapLatest { planets ->
                planets.map { PlanetUiState(it) }
            }
            .cachedIn(componentCoroutineScope())
    }

    override val planetsFlow by lazy {
        combine(
            planetsUiFlow,
            searchTextState,
            _selectedRegionsState
        ) { planets, searchText, selectRegions ->
            when {
                searchText.isBlank() -> planets.filterByRegions(selectRegions)
                else -> planets.filterBySearchQuery(searchText)
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val regionsFlow by lazy {
        combine(planetsUiFlow, _selectedRegionsState) { planets, regs ->
            planets
                .map { it.mainRegion ?: "" }
                .filter { it.isNotEmpty() }
                .to(regs)
        }.mapLatest { (allRegs, selectRegs) ->
            allRegs.map { RegionUiState(it, (it in selectRegs)) }
        }
    }
}

internal fun PagingData<PlanetUiState>.filterByRegions(regions: List<String?>) =
    when {
        null in regions -> this
        else -> filter { it.mainRegion in regions }
    }

internal fun PagingData<PlanetUiState>.filterBySearchQuery(searchText: String) =
    filter { searchText.lowercase() in it.title.lowercase() }