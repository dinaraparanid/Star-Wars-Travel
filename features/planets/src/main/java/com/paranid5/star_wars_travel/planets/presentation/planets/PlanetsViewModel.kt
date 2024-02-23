package com.paranid5.star_wars_travel.planets.presentation.planets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import androidx.paging.map
import com.paranid5.star_wars_travel.data.PlanetsRepository
import com.paranid5.star_wars_travel.impl.presentation.PlanetUiState
import com.paranid5.star_wars_travel.impl.presentation.mainRegion
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.update

class PlanetsViewModel(private val planetsRepository: PlanetsRepository) : ViewModel() {
    private val _searchTextState by lazy {
        MutableStateFlow("")
    }

    val searchTextState by lazy {
        _searchTextState.asStateFlow()
    }

    fun setSearchText(text: String) =
        _searchTextState.update { text }

    private val _selectedRegionsState by lazy {
        MutableStateFlow(listOf<String?>(null))
    }

    val selectedRegionsState by lazy {
        _selectedRegionsState.asStateFlow()
    }

    fun reselectRegion(region: String?) =
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
            .cachedIn(viewModelScope)
    }

    val planetsFlow by lazy {
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
    val regionsFlow by lazy {
        combine(planetsUiFlow, _selectedRegionsState) { planets, regs ->
            planets
                .map { it.mainRegion ?: "" }
                .filter { it.isNotEmpty() }
                .to(regs)
        }.mapLatest { (allRegs, selectRegs) ->
            allRegs.map { it to (it in selectRegs) }
        }
    }
}

fun PagingData<PlanetUiState>.filterByRegions(regions: List<String?>) =
    when {
        null in regions -> this
        else -> filter { it.mainRegion in regions }
    }

fun PagingData<PlanetUiState>.filterBySearchQuery(searchText: String) =
    filter { searchText in it.title.lowercase() }