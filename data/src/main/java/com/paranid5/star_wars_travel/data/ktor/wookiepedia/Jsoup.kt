package com.paranid5.star_wars_travel.data.ktor.wookiepedia

import com.paranid5.star_wars_travel.core.common.domain.entities.SwapiPlanet
import com.paranid5.star_wars_travel.core.common.domain.entities.wookiepedia.WookiepediaPlanet
import com.paranid5.star_wars_travel.data.ktor.USER_AGENT
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.jsoup.parser.Parser

private const val WOOKIEPEDIA_BASE_URL = "https://starwars.fandom.com/wiki"

internal val CITATION_REGEX = Regex("\\[\\d+]")

internal suspend fun PlanetDTO(planet: SwapiPlanet, pageNumber: Int) = coroutineScope {
    val html = WookieHtml(planet.name).getOrNull()
    val shortDescription = html?.shortDescription()?.getOrNull()

    WookiepediaPlanet(
        title = planet.name,
        edited = planet.edited,
        pageNumber = pageNumber,
        description = html?.planetDescription()?.getOrNull(),
        coverUrl = html?.urlCover()?.getOrNull(),
        astrographicalInformation = shortDescription
            ?.astrographicalInfo(planet)
            ?: defaultAstroInfo(planet),
        physicalInformation = shortDescription
            ?.physicalInfo(planet)
            ?: defaultPhysInfo(planet),
        societalInformation = shortDescription
            ?.societalInfo(planet)
            ?: defaultSocInfo(planet)
    )
}

internal suspend inline fun WookieHtml(query: String) =
    withContext(Dispatchers.IO) {
        runCatching {
            Jsoup
                .connect("$WOOKIEPEDIA_BASE_URL/${wookiepediaFormat(query)}")
                .ignoreContentType(true)
                .userAgent(USER_AGENT)
                .parser(Parser.xmlParser())
                .get()
        }
    }

private suspend inline fun Element.planetDescription() = runCatching {
    withContext(Dispatchers.IO) {
        select("div[class=mw-parser-output]")
            .firstOrNull()
            ?.children()
            ?.filterNot { it.tagName() != "p" }
            ?.drop(1)
            ?.firstOrNull()
            ?.text()
            ?.replace(CITATION_REGEX, "")
    }
}

private suspend inline fun Element.shortDescription() = runCatching {
    withContext(Dispatchers.IO) {
        select("aside[role=region]").firstOrNull()
    }
}

internal suspend inline fun Element.urlCover() = runCatching {
    withContext(Dispatchers.IO) {
        select("meta[property=og:image]")
            .attr("content")
    }
}

private fun wookiepediaFormat(planet: String) =
    planet.replace(" ", "_")