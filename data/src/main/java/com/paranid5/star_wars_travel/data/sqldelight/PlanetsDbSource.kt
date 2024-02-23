package com.paranid5.star_wars_travel.data.sqldelight

import com.paranid5.star_wars_travel.core.common.entities.wookiepedia.WookiepediaPlanet
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow

interface PlanetsDbSource {
    val planetsFlow: Flow<List<WookiepediaPlanet>>

    fun addPlanetAsync(planet: WookiepediaPlanet): Job
}