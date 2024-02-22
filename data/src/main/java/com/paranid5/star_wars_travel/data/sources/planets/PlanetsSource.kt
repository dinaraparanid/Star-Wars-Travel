package com.paranid5.star_wars_travel.data.sources.planets

import com.paranid5.star_wars_travel.core.common.entities.PlanetPage

interface PlanetsSource {
    suspend fun fetchPlanets(pageNum: Int = 1): PlanetPage
}