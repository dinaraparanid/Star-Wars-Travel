package com.paranid5.star_wars_travel.impl.presentation

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.paranid5.star_wars_travel.core.common.domain.entities.wookiepedia.PhysicalInformation
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Immutable
@Parcelize
@Serializable
data class PhysInfo(
    val climate: String,
    val gravity: String,
    val terrain: String,
    val surfaceWater: Int,
    val diameter: Int = 0,
    val planetClass: String? = null,
    val atmosphere: String? = null,
    val interest: Array<Interest> = emptyArray(),
    val flora: Array<String> = emptyArray(),
    val fauna: Array<String> = emptyArray()
) : Parcelable {
    constructor(infoEntity: PhysicalInformation) : this(
        climate = infoEntity.climate,
        gravity = infoEntity.gravity,
        terrain = infoEntity.terrain,
        surfaceWater = infoEntity.surfaceWater,
        diameter = infoEntity.diameter,
        planetClass = infoEntity.planetClass,
        atmosphere = infoEntity.atmosphere,
        interest = infoEntity.interest.map(::Interest).toTypedArray(),
        flora = infoEntity.flora.toTypedArray(),
        fauna = infoEntity.fauna.toTypedArray()
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PhysInfo

        if (climate != other.climate) return false
        if (gravity != other.gravity) return false
        if (terrain != other.terrain) return false
        if (surfaceWater != other.surfaceWater) return false
        if (diameter != other.diameter) return false
        if (planetClass != other.planetClass) return false
        if (atmosphere != other.atmosphere) return false
        if (!interest.contentEquals(other.interest)) return false
        if (!flora.contentEquals(other.flora)) return false
        if (!fauna.contentEquals(other.fauna)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = climate.hashCode()
        result = 31 * result + gravity.hashCode()
        result = 31 * result + terrain.hashCode()
        result = 31 * result + surfaceWater
        result = 31 * result + diameter
        result = 31 * result + (planetClass?.hashCode() ?: 0)
        result = 31 * result + (atmosphere?.hashCode() ?: 0)
        result = 31 * result + interest.contentHashCode()
        result = 31 * result + flora.contentHashCode()
        result = 31 * result + fauna.contentHashCode()
        return result
    }
}