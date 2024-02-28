package com.paranid5.star_wars_travel.impl.presentation

import androidx.compose.runtime.Immutable
import com.paranid5.star_wars_travel.core.common.domain.entities.wookiepedia.WookiepediaPlanet
import kotlinx.serialization.Serializable

@Immutable
@Serializable
data class PlanetUiState(
    val title: String,
    val astrographicalInformation: AstroInfo,
    val physicalInformation: PhysInfo,
    val societalInformation: SocInfo,
    val description: String? = null,
    val coverUrl: String? = null,
) {
    constructor(planetEntity: WookiepediaPlanet) : this(
        title = planetEntity.title,
        astrographicalInformation = AstroInfo(planetEntity.astrographicalInformation),
        physicalInformation = PhysInfo(planetEntity.physicalInformation),
        societalInformation = SocInfo(planetEntity.societalInformation),
        description = planetEntity.description,
        coverUrl = planetEntity.coverUrl
    )
}

inline val PlanetUiState.mainRegion
    get() = astrographicalInformation.region.firstOrNull()