package com.paranid5.star_wars_travel.data.ktor.wookiepedia

import com.paranid5.star_wars_travel.core.common.domain.entities.SwapiPlanet
import com.paranid5.star_wars_travel.core.common.domain.entities.wookiepedia.AstrographicalInformation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.nodes.Element

internal suspend inline fun Element.astrographicalInfo(planet: SwapiPlanet) =
    withContext(Dispatchers.IO) {
        AstrographicalInformation(
            rotationPeriod = planet.rotationPeriod.toIntOrNull() ?: 0,
            orbitalPeriod = planet.orbitalPeriod.toIntOrNull() ?: 0,
            region = info("region"),
            sector = info("sector").firstOrNull(),
            system = info("system").firstOrNull(),
            moons = info("moons").firstOrNull()?.toIntOrNull() ?: 0,
            tradeRoutes = info("routes")
        )
    }

internal fun defaultAstroInfo(planet: SwapiPlanet) =
    AstrographicalInformation(
        rotationPeriod = planet.rotationPeriod.toIntOrNull() ?: 0,
        orbitalPeriod = planet.orbitalPeriod.toIntOrNull() ?: 0,
    )