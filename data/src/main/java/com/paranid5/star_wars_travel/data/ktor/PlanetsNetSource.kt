package com.paranid5.star_wars_travel.data.ktor

import com.paranid5.star_wars_travel.core.common.domain.entities.PlanetPage
import com.paranid5.star_wars_travel.data.ktor.swapi.SwapiPlanetPage

interface PlanetsNetSource {
    suspend fun fetchSwapiPlanetPage(pageNum: Int = 1): SwapiPlanetPage

    suspend fun fetchCompletePlanetPage(pageNum: Int = 1): PlanetPage
}