package com.paranid5.star_wars_travel.core.common.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SwapiPlanet(
    val name: String,
    @SerialName("rotation_period") val rotationPeriod: Int,
    @SerialName("orbital_period") val orbitalPeriod: Int,
    val diameter: Int,
    val climate: String,
    val gravity: String,
    val terrain: String,
    @SerialName("surface_water") val surfaveWater: String,
    val population: String,
)
