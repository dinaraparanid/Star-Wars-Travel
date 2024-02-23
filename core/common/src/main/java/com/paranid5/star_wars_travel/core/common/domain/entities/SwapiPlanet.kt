package com.paranid5.star_wars_travel.core.common.domain.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SwapiPlanet(
    val name: String,
    @SerialName("rotation_period") val rotationPeriod: String,
    @SerialName("orbital_period") val orbitalPeriod: String,
    val diameter: String,
    val climate: String,
    val gravity: String,
    val terrain: String,
    @SerialName("surface_water") val surfaveWater: String,
    val population: String,
    val edited: String
)
