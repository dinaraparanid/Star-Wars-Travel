package com.paranid5.star_wars_travel.data.ktor.wookiepedia

import com.paranid5.star_wars_travel.core.common.entities.SwapiPlanet
import com.paranid5.star_wars_travel.core.common.entities.wookiepedia.AstrographicalInformation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.nodes.Element

internal suspend inline fun Element.astrographicalInfo(planet: SwapiPlanet) =
    withContext(Dispatchers.IO) {
        AstrographicalInformation(
            rotationPeriod = planet.rotationPeriod,
            orbitalPeriod = planet.orbitalPeriod,
            region = info("region"),
            sector = info("sector").firstOrNull(),
            system = info("system").firstOrNull(),
            moons = info("moons").firstOrNull()?.toIntOrNull() ?: 0,
            tradeRoutes = info("routes")
        )
    }

internal fun defaultAstroInfo(planet: SwapiPlanet) =
    AstrographicalInformation(
        rotationPeriod = planet.rotationPeriod,
        orbitalPeriod = planet.orbitalPeriod,
    )