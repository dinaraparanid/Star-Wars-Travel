package com.paranid5.star_wars_travel.navigation.composition_locals

import androidx.compose.runtime.staticCompositionLocalOf
import com.paranid5.star_wars_travel.navigation.component.RootComponent

val LocalNavigator = staticCompositionLocalOf<RootComponent?> { null }