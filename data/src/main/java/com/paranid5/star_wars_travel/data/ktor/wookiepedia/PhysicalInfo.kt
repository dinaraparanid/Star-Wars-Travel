package com.paranid5.star_wars_travel.data.ktor.wookiepedia

import com.paranid5.star_wars_travel.core.common.domain.entities.SwapiPlanet
import com.paranid5.star_wars_travel.core.common.domain.entities.wookiepedia.Interest
import com.paranid5.star_wars_travel.core.common.domain.entities.wookiepedia.PhysicalInformation
import com.paranid5.star_wars_travel.core.common.domain.utils.toIntOrZero
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.nodes.Element

internal suspend inline fun Element.physicalInfo(planet: SwapiPlanet) =
    withContext(Dispatchers.IO) {
        PhysicalInformation(
            climate = planet.climate,
            gravity = planet.gravity,
            terrain = planet.terrain,
            surfaceWater = planet.surfaveWater.toIntOrZero(),
            diameter = planet.diameter.toIntOrZero(),
            planetClass = info("class").firstOrNull(),
            atmosphere = info("atmosphere").firstOrNull(),
            interest = info("interest").map { Interest(it, null) },
            flora = info("flora"),
            fauna = info("fauna")
        )
    }

internal fun defaultPhysInfo(planet: SwapiPlanet) =
    PhysicalInformation(
        climate = planet.climate,
        gravity = planet.gravity,
        terrain = planet.terrain,
        surfaceWater = planet.surfaveWater.toIntOrZero(),
        diameter = planet.diameter.toIntOrZero()
    )