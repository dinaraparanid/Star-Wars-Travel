package com.paranid5.star_wars_travel.planets.presentation.planets

import androidx.lifecycle.ViewModel
import com.paranid5.star_wars_travel.core.common.entities.wookiepedia.WookiepediaPlanet
import com.paranid5.star_wars_travel.data.PlanetsRepository
import com.paranid5.star_wars_travel.impl.presentation.PlanetUiState
import com.paranid5.star_wars_travel.impl.presentation.mainRegion
import com.paranid5.star_wars_travel.impl.use_case.filterByRegions
import com.paranid5.star_wars_travel.impl.use_case.filterBySearchQuery
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.update

class PlanetsViewModel(private val planetsRepository: PlanetsRepository) : ViewModel() {
    private val planetsPagesState by lazy {
        MutableStateFlow(emptyMap<Int, List<String>>())
    }

    private val _searchTextState by lazy {
        MutableStateFlow("")
    }

    val searchTextState by lazy {
        _searchTextState.asStateFlow()
    }

    fun setSearchText(text: String) =
        _searchTextState.update { text }

    private val selectedRegionsState by lazy {
        MutableStateFlow(listOf<String?>(null))
    }

    fun reselectRegion(region: String?) =
        when (region) {
            null -> selectedRegionsState.update { listOf(null) }
            else -> selectedRegionsState.update {
                when {
                    region in it -> if (it.size == 1) listOf(null) else it - region
                    else -> it - null + region
                }
            }
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    private val planetsUiFlow by lazy {
        planetsRepository
            .planetsFlow
            .mapLatest { planets ->
                planets
                    .map(::PlanetUiState)
                    .toPersistentList()
            }
    }

    val planetsFlow by lazy {
        combine(
            planetsUiFlow,
            searchTextState,
            selectedRegionsState
        ) { planets, searchText, selectRegions ->
            when {
                searchText.isBlank() -> planets.filterByRegions(selectRegions)
                else -> planets.filterBySearchQuery(searchText)
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val regionsFlow by lazy {
        combine(planetsUiFlow, selectedRegionsState) { planets, regs ->
            persistentListOf(null) + planets.mapNotNull { it.mainRegion }.distinct() to regs
        }.mapLatest { (allRegs, selectRegs) ->
            allRegs.map { it to (it in selectRegs) }
        }
    }

    suspend fun fetchAndStorePlanets(pageNum: Int = 1) {
        val planets = planetsRepository
            .fetchPlanets(pageNum)
            .planets

        planetsPagesState.update {
            it + mapOf(pageNum to planets.map(WookiepediaPlanet::title))
        }

        planets.forEach {
            planetsRepository.addPlanetAsync(it)
        }
    }
}