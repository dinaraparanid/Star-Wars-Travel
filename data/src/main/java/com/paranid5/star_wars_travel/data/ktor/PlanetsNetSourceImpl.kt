package com.paranid5.star_wars_travel.data.ktor

import com.paranid5.star_wars_travel.core.common.domain.entities.PlanetPage
import io.ktor.client.HttpClient

class PlanetsNetSourceImpl(private val ktorClient: HttpClient) : PlanetsNetSource {
    override suspend fun fetchPlanetPage(pageNum: Int): PlanetPage =
        ktorClient.getPlanets(pageNum)
}