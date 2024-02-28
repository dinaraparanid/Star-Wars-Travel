package com.paranid5.star_wars_travel.data.ktor

import com.paranid5.star_wars_travel.core.common.domain.entities.PlanetPage
import com.paranid5.star_wars_travel.data.ktor.swapi.SwapiPlanetPage
import com.paranid5.star_wars_travel.data.ktor.swapi.getSwapiPlanets
import io.ktor.client.HttpClient

internal class PlanetsNetSourceImpl(private val ktorClient: HttpClient) : PlanetsNetSource {
    override suspend fun fetchSwapiPlanetPage(pageNum: Int): SwapiPlanetPage =
        ktorClient
            .getSwapiPlanets(pageNum)
            .getOrElse {
                SwapiPlanetPage(0, null, null, emptyList())
            }

    override suspend fun fetchCompletePlanetPage(pageNum: Int): PlanetPage =
        ktorClient.getPlanets(pageNum)
}