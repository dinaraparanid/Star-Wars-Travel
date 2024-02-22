package com.paranid5.star_wars_travel.data.sources.planets

import com.paranid5.star_wars_travel.core.common.entities.PlanetPage
import com.paranid5.star_wars_travel.data.ktor.getPlanets
import io.ktor.client.HttpClient

class PlanetsSourceImpl(private val ktorClient: HttpClient) : PlanetsSource {
    override suspend fun fetchPlanets(pageNum: Int): PlanetPage =
        ktorClient.getPlanets(pageNum)
}