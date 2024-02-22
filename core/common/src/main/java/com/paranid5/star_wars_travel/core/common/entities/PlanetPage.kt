package com.paranid5.star_wars_travel.core.common.entities

import com.paranid5.star_wars_travel.core.common.entities.wookiepedia.WookiepediaPlanet

data class PlanetPage(
    val next: String?,
    val previous: String?,
    val planets: List<WookiepediaPlanet>
)