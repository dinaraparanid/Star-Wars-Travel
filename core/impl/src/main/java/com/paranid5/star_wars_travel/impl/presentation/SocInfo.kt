package com.paranid5.star_wars_travel.impl.presentation

import androidx.compose.runtime.Immutable
import com.paranid5.star_wars_travel.core.common.domain.entities.wookiepedia.SocietalInformation
import kotlinx.serialization.Serializable

@Immutable
@Serializable
data class SocInfo(
    val population: Long,
    val nativeSpecies: List<String> = emptyList(),
    val otherSpecies: List<String> = emptyList(),
    val primaryLanguages: List<String> = emptyList(),
    val government: String? = null,
    val majorCities: List<String> = emptyList()
) {
    constructor(infoEntity: SocietalInformation) : this(
        population = infoEntity.population,
        nativeSpecies = infoEntity.nativeSpecies,
        otherSpecies = infoEntity.otherSpecies,
        primaryLanguages = infoEntity.primaryLanguages,
        government = infoEntity.government,
        majorCities = infoEntity.majorCities
    )
}