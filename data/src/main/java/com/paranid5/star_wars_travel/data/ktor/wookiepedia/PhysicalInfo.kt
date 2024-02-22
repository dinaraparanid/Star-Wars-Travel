package com.paranid5.star_wars_travel.data.ktor.wookiepedia

import com.paranid5.star_wars_travel.core.common.entities.SwapiPlanet
import com.paranid5.star_wars_travel.core.common.entities.wookiepedia.PhysicalInformation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.nodes.Element

internal suspend inline fun Element.physicalInfo(planet: SwapiPlanet) =
    withContext(Dispatchers.IO) {
        PhysicalInformation(
            climate = planet.climate,
            gravity = planet.gravity,
            terrain = planet.terrain,
            surfaceWater = planet.surfaveWater.toIntOrNull() ?: 0,
            diameter = planet.diameter,
            planetClass = physInfo("class").firstOrNull(),
            atmosphere = physInfo("atmosphere").firstOrNull(),
            interest = physInfo("interest"),
            flora = physInfo("flora"),
            fauna = physInfo("fauna")
        )
    }

private fun Element.physInfo(selector: String) =
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

internal fun defaultPhysInfo(planet: SwapiPlanet) =
    PhysicalInformation(
        climate = planet.climate,
        gravity = planet.gravity,
        terrain = planet.terrain,
        surfaceWater = planet.surfaveWater.toIntOrNull() ?: 0,
        diameter = planet.diameter
    )