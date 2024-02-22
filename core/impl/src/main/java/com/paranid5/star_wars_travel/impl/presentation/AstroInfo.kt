package com.paranid5.star_wars_travel.impl.presentation

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.paranid5.star_wars_travel.core.common.entities.wookiepedia.AstrographicalInformation
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class AstroInfo(
    val rotationPeriod: Int,
    val orbitalPeriod: Int,
    val region: Array<String> = emptyArray(),
    val sector: String? = null,
    val system: String? = null,
    val moons: Int = 0,
    val tradeRoutes: Array<String> = emptyArray(),
) : Parcelable {
    constructor(infoEntity: AstrographicalInformation) : this(
        rotationPeriod = infoEntity.rotationPeriod,
        orbitalPeriod = infoEntity.orbitalPeriod,
        region = infoEntity.region.toTypedArray(),
        sector = infoEntity.sector,
        system = infoEntity.system,
        moons = infoEntity.moons,
        tradeRoutes = infoEntity.tradeRoutes.toTypedArray()
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AstroInfo

        if (rotationPeriod != other.rotationPeriod) return false
        if (orbitalPeriod != other.orbitalPeriod) return false
        if (!region.contentEquals(other.region)) return false
        if (sector != other.sector) return false
        if (system != other.system) return false
        if (moons != other.moons) return false
        if (!tradeRoutes.contentEquals(other.tradeRoutes)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = rotationPeriod
        result = 31 * result + orbitalPeriod
        result = 31 * result + region.contentHashCode()
        result = 31 * result + (sector?.hashCode() ?: 0)
        result = 31 * result + (system?.hashCode() ?: 0)
        result = 31 * result + moons
        result = 31 * result + tradeRoutes.contentHashCode()
        return result
    }
}