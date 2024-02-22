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
            region = astroInfo("region"),
            sector = astroInfo("sector").firstOrNull(),
            system = astroInfo("system").firstOrNull(),
            moons = astroInfo("moons").firstOrNull()?.toIntOrNull() ?: 0,
            tradeRoutes = astroInfo("routes")
        )
    }

private fun Element.astroInfo(selector: String) =
    runCatching {
        select("div[*=$selector]")
            .select("div")
            .filterNot {
                it.tagName() != "sup"
                        && it.tagName() != "h3"
                        && it.text() != "unknown"
            }
            .map(Element::text)
    }.getOrElse { emptyList() }

internal fun defaultAstroInfo(planet: SwapiPlanet) =
    AstrographicalInformation(
        rotationPeriod = planet.rotationPeriod,
        orbitalPeriod = planet.orbitalPeriod,
    )