package com.paranid5.star_wars_travel.impl.presentation

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.paranid5.star_wars_travel.core.common.entities.wookiepedia.WookiepediaPlanet
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class PlanetUiState(
    val title: String,
    val astrographicalInformation: AstroInfo,
    val physicalInformation: PhysInfo,
    val societalInformation: SocInfo,
    val description: String? = null,
    val coverUrl: String? = null,
) : Parcelable {
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