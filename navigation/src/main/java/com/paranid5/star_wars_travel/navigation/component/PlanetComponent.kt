package com.paranid5.star_wars_travel.navigation.component

import com.arkivanov.decompose.ComponentContext
import com.paranid5.star_wars_travel.core.common.domain.entities.wookiepedia.WookiepediaPlanet

class PlanetComponent(val planet: WookiepediaPlanet, componentContext: ComponentContext) :
    ComponentContext by componentContext