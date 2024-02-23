package com.paranid5.star_wars_travel.data.sqldelight

import com.paranid5.star_wars_travel.core.common.domain.entities.wookiepedia.WookiepediaPlanet
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow

interface PlanetsDbSource {
    val planetsFlow: Flow<List<WookiepediaPlanet>>

    suspend fun getPlanets(): List<WookiepediaPlanet>

    fun addPlanetAsync(planet: WookiepediaPlanet): Job
}