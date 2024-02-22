package com.paranid5.star_wars_travel.navigation.component

sealed interface RootComponentChild {
    data class PlanetsChild(val component: PlanetsComponent) : RootComponentChild
    data class PlanetChild(val component: PlanetComponent) : RootComponentChild
    data class SettingsChild(val component: SettingsComponent) : RootComponentChild
    data class AboutAppChild(val component: AboutAppComponent) : RootComponentChild
}