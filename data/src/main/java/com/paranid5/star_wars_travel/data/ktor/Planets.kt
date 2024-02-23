package com.paranid5.star_wars_travel.data.ktor

import com.paranid5.star_wars_travel.core.common.domain.entities.PlanetPage
import com.paranid5.star_wars_travel.core.common.domain.entities.wookiepedia.WookiepediaPlanet
import com.paranid5.star_wars_travel.data.ktor.swapi.getSwapiPlanets
import com.paranid5.star_wars_travel.data.ktor.wookiepedia.PlanetDTO
import io.ktor.client.HttpClient

suspend fun HttpClient.getPlanets(pageNum: Int = 1) =
    getSwapiPlanets(pageNum).let { (_, next, previous, results) ->
        PlanetPage(
            next = next,
            previous = previous,
            planets = results
                .map { PlanetDTO(it) }
                .sortedBy(WookiepediaPlanet::edited)
        )
    }