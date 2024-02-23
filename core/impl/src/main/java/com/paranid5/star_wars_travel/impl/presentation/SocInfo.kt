package com.paranid5.star_wars_travel.impl.presentation

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.paranid5.star_wars_travel.core.common.entities.wookiepedia.SocietalInformation
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
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
}