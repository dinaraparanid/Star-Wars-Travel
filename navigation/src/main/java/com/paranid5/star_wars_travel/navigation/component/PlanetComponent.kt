package com.paranid5.star_wars_travel.navigation.component

import com.arkivanov.decompose.ComponentContext
import com.paranid5.star_wars_travel.core.common.entities.Planet

class PlanetComponent(val planet: Planet, componentContext: ComponentContext) :
    ComponentContext by componentContext