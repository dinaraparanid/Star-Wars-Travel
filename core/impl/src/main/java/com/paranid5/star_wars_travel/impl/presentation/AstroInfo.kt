package com.paranid5.star_wars_travel.impl.presentation

import androidx.compose.runtime.Immutable
import com.paranid5.star_wars_travel.core.common.domain.entities.wookiepedia.AstrographicalInformation
import kotlinx.serialization.Serializable

@Immutable
@Serializable
data class AstroInfo(
    val rotationPeriod: Int,
    val orbitalPeriod: Int,
    val region: List<String> = emptyList(),
    val sector: String? = null,
    val system: String? = null,
    val moons: Int = 0,
    val tradeRoutes: List<String> = emptyList(),
) {
    constructor(infoEntity: AstrographicalInformation) : this(
        rotationPeriod = infoEntity.rotationPeriod,
        orbitalPeriod = infoEntity.orbitalPeriod,
        region = infoEntity.region,
        sector = infoEntity.sector,
        system = infoEntity.system,
        moons = infoEntity.moons,
        tradeRoutes = infoEntity.tradeRoutes
    )
}