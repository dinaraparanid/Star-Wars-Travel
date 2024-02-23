package com.paranid5.star_wars_travel.data.ktor

import com.paranid5.star_wars_travel.core.common.entities.PlanetPage

interface PlanetsNetSource {
    suspend fun fetchPlanets(pageNum: Int = 1): PlanetPage
}