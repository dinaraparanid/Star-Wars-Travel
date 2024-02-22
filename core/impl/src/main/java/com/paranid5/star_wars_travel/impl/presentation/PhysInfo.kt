package com.paranid5.star_wars_travel.impl.presentation

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.paranid5.star_wars_travel.core.common.entities.wookiepedia.PhysicalInformation
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class PhysInfo(
    val climate: String,
    val gravity: String,
    val terrain: String,
    val surfaceWater: Int,
    val diameter: Int = 0,
    val planetClass: String? = null,
    val atmosphere: String? = null,
    val interest: Array<String> = emptyArray(),
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
        interest = infoEntity.interest.toTypedArray(),
        flora = infoEntity.flora.toTypedArray(),
        fauna = infoEntity.fauna.toTypedArray()
    )
}