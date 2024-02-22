package com.paranid5.star_wars_travel.presentation.composition_locals

import androidx.compose.runtime.staticCompositionLocalOf
import com.paranid5.star_wars_travel.navigation.component.RootNavigator

val LocalNavigator = staticCompositionLocalOf<RootNavigator?> { null }