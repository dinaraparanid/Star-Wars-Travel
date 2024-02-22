package com.paranid5.star_wars_travel.planets.presentation.planets

import androidx.lifecycle.ViewModel
import com.paranid5.star_wars_travel.core.common.entities.PlanetPage
import com.paranid5.star_wars_travel.data.sources.planets.PlanetsSource
import com.paranid5.star_wars_travel.data.sources.planets.PlanetsSourceImpl
import com.paranid5.star_wars_travel.impl.presentation.PlanetUiState
import io.ktor.client.HttpClient
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class PlanetsViewModule(ktorClient: HttpClient) : ViewModel(), PlanetsSource by PlanetsSourceImpl(ktorClient) {
    private val planetsPagesState by lazy {
        MutableStateFlow(emptyMap<Int, PlanetPage>())
    }

    val planetsFlow by lazy {
        planetsPagesState.map {
            it.values
                .flatMap(PlanetPage::planets)
                .map(::PlanetUiState)
                .toPersistentList()
        }
    }

    suspend fun fetchAndStorePlanets(pageNum: Int = 1) {
        val page = fetchPlanets(pageNum)

         planetsPagesState.update {
             it + mapOf(pageNum to page)
         }
    }
}