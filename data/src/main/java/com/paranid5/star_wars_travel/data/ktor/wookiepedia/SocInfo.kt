package com.paranid5.star_wars_travel.data.ktor.wookiepedia

import com.paranid5.star_wars_travel.core.common.entities.SwapiPlanet
import com.paranid5.star_wars_travel.core.common.entities.wookiepedia.SocietalInformation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.nodes.Element

internal suspend inline fun Element.societalInfo(planet: SwapiPlanet) =
    withContext(Dispatchers.IO) {
        SocietalInformation(
            population = planet.population.toIntOrNull() ?: 0,
            nativeSpecies = socInfo("species"),
            otherSpecies = socInfo("otherspecies"),
            primaryLanguages = socInfo("language"),
            government = socInfo("government").firstOrNull(),
            majorCities = socInfo("cities"),
        )
    }

private fun Element.socInfo(selector: String) =
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

internal fun defaultSocInfo(planet: SwapiPlanet) =
    SocietalInformation(population = planet.population.toIntOrNull() ?: 0)