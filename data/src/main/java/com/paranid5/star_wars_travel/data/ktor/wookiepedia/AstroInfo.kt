package com.paranid5.star_wars_travel.data.ktor.wookiepedia

import com.paranid5.star_wars_travel.core.common.domain.entities.SwapiPlanet
import com.paranid5.star_wars_travel.core.common.domain.entities.wookiepedia.AstrographicalInformation
import com.paranid5.star_wars_travel.core.common.domain.utils.toIntOrZero
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.nodes.Element

internal suspend inline fun Element.astrographicalInfo(planet: SwapiPlanet) =
    withContext(Dispatchers.IO) {
        AstrographicalInformation(
            rotationPeriod = planet.rotationPeriod.toIntOrZero(),
            orbitalPeriod = planet.orbitalPeriod.toIntOrZero(),
            region = info("region"),
            sector = info("sector").firstOrNull(),
            system = info("system").firstOrNull(),
            moons = info("moons").firstOrNull().toIntOrZero(),
            tradeRoutes = info("routes")
        )
    }

internal fun defaultAstroInfo(planet: SwapiPlanet) =
    AstrographicalInformation(
        rotationPeriod = planet.rotationPeriod.toIntOrZero(),
        orbitalPeriod = planet.orbitalPeriod.toIntOrZero(),
    )