package com.paranid5.star_wars_travel.data.ktor

import com.paranid5.star_wars_travel.core.common.domain.entities.PlanetPage
import com.paranid5.star_wars_travel.core.common.domain.entities.wookiepedia.WookiepediaPlanet
import com.paranid5.star_wars_travel.data.ktor.swapi.SwapiPlanetPage
import com.paranid5.star_wars_travel.data.ktor.swapi.getSwapiPlanets
import com.paranid5.star_wars_travel.data.ktor.wookiepedia.PlanetDTO
import io.ktor.client.HttpClient

suspend fun HttpClient.getPlanets(pageNumber: Int = 1) =
    getSwapiPlanets(pageNumber)
        .getOrElse {
            SwapiPlanetPage(0, null, null, emptyList())
        }
        .let { (_, next, previous, results) ->
            PlanetPage(
                next = next,
                previous = previous,
                planets = results
                    .map { PlanetDTO(planet = it, pageNumber = pageNumber) }
                    .sortedBy(WookiepediaPlanet::edited)
            )
        }