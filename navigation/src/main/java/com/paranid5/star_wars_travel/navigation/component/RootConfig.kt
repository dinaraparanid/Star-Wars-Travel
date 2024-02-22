package com.paranid5.star_wars_travel.navigation.component

import com.paranid5.star_wars_travel.core.common.entities.wookiepedia.WookiepediaPlanet
import kotlinx.serialization.Serializable

@Serializable
internal sealed interface RootConfig {
    @Serializable
    data object Planets : RootConfig

    @Serializable
    data class Planet(val planet: WookiepediaPlanet) : RootConfig

    @Serializable
    data object Settings: RootConfig

    @Serializable
    data object AboutApp : RootConfig
}