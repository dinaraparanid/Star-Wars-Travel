package com.paranid5.star_wars_travel.resources.ui

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.RippleConfiguration
import androidx.compose.material.RippleDefaults

private val SWRippleAlpha =
    RippleDefaults.rippleAlpha(
        contentColor = StarWarsYellow,
        lightTheme = true
    )

@OptIn(ExperimentalMaterialApi::class)
val SWRippleConfinguration =
    RippleConfiguration(
        color = StarWarsYellow,
        rippleAlpha = SWRippleAlpha
    )