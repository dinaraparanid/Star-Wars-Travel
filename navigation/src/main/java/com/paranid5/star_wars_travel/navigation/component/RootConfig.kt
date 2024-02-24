package com.paranid5.star_wars_travel.navigation.component

import com.paranid5.star_wars_travel.core.common.domain.entities.wookiepedia.WookiepediaPlanet
import com.paranid5.star_wars_travel.impl.presentation.PlanetUiState
import kotlinx.serialization.Serializable

@Serializable
sealed interface RootConfig {
    @Serializable
    data object Planets : RootConfig

    @Serializable
    data class Planet(val planet: PlanetUiState) : RootConfig

    @Serializable
    data object Settings: RootConfig

    @Serializable
    data object AboutApp : RootConfig
}