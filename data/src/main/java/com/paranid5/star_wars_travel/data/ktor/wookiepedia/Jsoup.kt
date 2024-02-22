package com.paranid5.star_wars_travel.data.ktor.wookiepedia

import android.util.Log
import com.paranid5.star_wars_travel.core.common.entities.SwapiPlanet
import com.paranid5.star_wars_travel.core.common.entities.wookiepedia.WookiepediaPlanet
import com.paranid5.star_wars_travel.data.ktor.USER_AGENT
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.jsoup.parser.Parser

private const val WOOKIEPEDIA_BASE_URL = "https://starwars.fandom.com/wiki"

suspend fun PlanetDTO(planet: SwapiPlanet) = coroutineScope {
    val html = PlanetHtml(planet.name)
    val shortDescription = html.shortDescription().getOrNull()
    Log.d("BEBRA", "Image: ${html.planetUrlCover().getOrNull()}")

    WookiepediaPlanet(
        title = planet.name,
        description = html.planetDescription().getOrNull(),
        coverUrl = html.planetUrlCover().getOrNull(),
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

private suspend inline fun PlanetHtml(planet: String): Element =
    withContext(Dispatchers.IO) {
        Jsoup
            .connect("$WOOKIEPEDIA_BASE_URL/${wookiepediaFormat(planet)}")
            .ignoreContentType(true)
            .userAgent(USER_AGENT)
            .parser(Parser.xmlParser())
            .get()
    }

private suspend inline fun Element.planetDescription() = runCatching {
    withContext(Dispatchers.IO) {
        select("meta[property=og:description]")
            .attr("content")
    }
}

private suspend inline fun Element.shortDescription() = runCatching {
    withContext(Dispatchers.IO) {
        select("aside[*=region]")
            .select("section")
            .firstOrNull()
    }
}

private suspend inline fun Element.planetUrlCover() = runCatching {
    withContext(Dispatchers.IO) {
        select("meta[property=og:image]")
            .attr("content")
    }
}

private fun wookiepediaFormat(planet: String) =
    planet.replace(" ", "_")