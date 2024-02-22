package com.paranid5.star_wars_travel.navigation.component

import kotlinx.serialization.Serializable
import com.paranid5.star_wars_travel.core.common.entities.Planet as PlanetEntity

@Serializable
internal sealed interface RootConfig {
    @Serializable
    data object Planets : RootConfig

    @Serializable
    data class Planet(val planet: PlanetEntity) : RootConfig

    @Serializable
    data object Settings: RootConfig

    @Serializable
    data object AboutApp : RootConfig
}