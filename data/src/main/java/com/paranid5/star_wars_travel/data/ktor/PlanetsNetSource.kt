package com.paranid5.star_wars_travel.data.ktor

import com.paranid5.star_wars_travel.core.common.domain.entities.PlanetPage

interface PlanetsNetSource {
    suspend fun fetchPlanetPage(pageNum: Int = 1): PlanetPage
}