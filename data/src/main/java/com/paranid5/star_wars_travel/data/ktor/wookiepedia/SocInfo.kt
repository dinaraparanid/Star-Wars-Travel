package com.paranid5.star_wars_travel.data.ktor.wookiepedia

import com.paranid5.star_wars_travel.core.common.entities.SwapiPlanet
import com.paranid5.star_wars_travel.core.common.entities.wookiepedia.SocietalInformation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.nodes.Element

internal suspend inline fun Element.societalInfo(planet: SwapiPlanet) =
    withContext(Dispatchers.IO) {
        SocietalInformation(
            population = planet.population.toLongOrNull() ?: 0L,
            nativeSpecies = info("species"),
            otherSpecies = info("otherspecies"),
            primaryLanguages = info("language"),
            government = info("government").firstOrNull(),
            majorCities = info("cities"),
        )
    }

internal fun defaultSocInfo(planet: SwapiPlanet) =
    SocietalInformation(population = planet.population.toLongOrNull() ?: 0L)