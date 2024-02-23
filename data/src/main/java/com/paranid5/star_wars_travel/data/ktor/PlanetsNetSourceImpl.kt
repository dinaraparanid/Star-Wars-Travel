package com.paranid5.star_wars_travel.data.ktor

import com.paranid5.star_wars_travel.core.common.entities.PlanetPage
import io.ktor.client.HttpClient

class PlanetsNetSourceImpl(private val ktorClient: HttpClient) : PlanetsNetSource {
    override suspend fun fetchPlanets(pageNum: Int): PlanetPage =
        ktorClient.getPlanets(pageNum)
}