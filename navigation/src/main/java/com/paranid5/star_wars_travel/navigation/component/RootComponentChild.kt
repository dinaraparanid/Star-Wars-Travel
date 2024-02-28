package com.paranid5.star_wars_travel.navigation.component

import com.paranid5.star_wars_travel.navigation.component.about_app.AboutAppComponent
import com.paranid5.star_wars_travel.navigation.component.planet.PlanetComponent
import com.paranid5.star_wars_travel.navigation.component.planets.PlanetsComponent
import com.paranid5.star_wars_travel.navigation.component.settings.SettingsComponent

sealed interface RootComponentChild {
    data class PlanetsChild(val component: PlanetsComponent) : RootComponentChild
    data class PlanetChild(val component: PlanetComponent) : RootComponentChild
    data class SettingsChild(val component: SettingsComponent) : RootComponentChild
    data class AboutAppChild(val component: AboutAppComponent) : RootComponentChild
}