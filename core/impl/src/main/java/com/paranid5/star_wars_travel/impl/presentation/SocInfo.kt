package com.paranid5.star_wars_travel.impl.presentation

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.paranid5.star_wars_travel.core.common.domain.entities.wookiepedia.SocietalInformation
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Immutable
@Parcelize
@Serializable
data class SocInfo(
    val population: Long,
    val nativeSpecies: Array<String> = emptyArray(),
    val otherSpecies: Array<String> = emptyArray(),
    val primaryLanguages: Array<String> = emptyArray(),
    val government: String? = null,
    val majorCities: Array<String> = emptyArray()
) : Parcelable {
    constructor(infoEntity: SocietalInformation) : this(
        population = infoEntity.population,
        nativeSpecies = infoEntity.nativeSpecies.toTypedArray(),
        otherSpecies = infoEntity.otherSpecies.toTypedArray(),
        primaryLanguages = infoEntity.primaryLanguages.toTypedArray(),
        government = infoEntity.government,
        majorCities = infoEntity.majorCities.toTypedArray()
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SocInfo

        if (population != other.population) return false
        if (!nativeSpecies.contentEquals(other.nativeSpecies)) return false
        if (!otherSpecies.contentEquals(other.otherSpecies)) return false
        if (!primaryLanguages.contentEquals(other.primaryLanguages)) return false
        if (government != other.government) return false
        if (!majorCities.contentEquals(other.majorCities)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = population.hashCode()
        result = 31 * result + nativeSpecies.contentHashCode()
        result = 31 * result + otherSpecies.contentHashCode()
        result = 31 * result + primaryLanguages.contentHashCode()
        result = 31 * result + (government?.hashCode() ?: 0)
        result = 31 * result + majorCities.contentHashCode()
        return result
    }
}