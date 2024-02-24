package com.paranid5.star_wars_travel.navigation.component

import com.arkivanov.decompose.ComponentContext
import com.paranid5.star_wars_travel.impl.presentation.PlanetUiState

class PlanetComponent(val planet: PlanetUiState, componentContext: ComponentContext) :
    ComponentContext by componentContext