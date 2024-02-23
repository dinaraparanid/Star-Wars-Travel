package com.paranid5.star_wars_travel.planets.presentation.planets

import androidx.lifecycle.ViewModel
import com.paranid5.star_wars_travel.core.common.entities.wookiepedia.WookiepediaPlanet
import com.paranid5.star_wars_travel.data.PlanetsRepository
import com.paranid5.star_wars_travel.impl.presentation.PlanetUiState
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class PlanetsViewModule(private val planetsRepository: PlanetsRepository) : ViewModel() {
    private val planetsPagesState by lazy {
        MutableStateFlow(emptyMap<Int, List<String>>())
    }

    val planetsFlow by lazy {
        planetsRepository.planetsFlow.map {
            it.map(::PlanetUiState).toPersistentList()
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