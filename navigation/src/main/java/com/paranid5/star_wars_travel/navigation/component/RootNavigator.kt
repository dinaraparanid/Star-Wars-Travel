package com.paranid5.star_wars_travel.navigation.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.paranid5.star_wars_travel.core.common.entities.wookiepedia.WookiepediaPlanet

class RootNavigator(componentContext: ComponentContext) : ComponentContext by componentContext {
    private val navigation = StackNavigation<RootConfig>()

    val stack: Value<ChildStack<*, RootComponentChild>> =
        childStack(
            source = navigation,
            serializer = RootConfig.serializer(),
            initialConfiguration = RootConfig.Planets,
            handleBackButton = true,
            childFactory = ::child
        )

    fun navigateToPlanets() =
        navigation.bringToFront(RootConfig.Planets)

    fun navigateToPlanet(planet: WookiepediaPlanet) =
        navigation.bringToFront(RootConfig.Planet(planet))

    fun navigateToSettings() =
        navigation.bringToFront(RootConfig.Settings)

    fun navigateToAboutApp() =
        navigation.bringToFront(RootConfig.AboutApp)

    infix fun navigateTo(screen: RootComponentChild) =
        when (screen) {
            is RootComponentChild.PlanetsChild -> navigateToPlanets()
            is RootComponentChild.PlanetChild -> navigateToPlanet(screen.component.planet)
            is RootComponentChild.SettingsChild -> navigateToSettings()
            is RootComponentChild.AboutAppChild -> navigateToAboutApp()
        }

    inline val planetsComponent
        get() = PlanetsComponent(this)

    fun planetComponent(planet: WookiepediaPlanet) =
        PlanetComponent(planet, this)

    inline val aboutAppComponent
        get() = AboutAppComponent(this)

    inline val settingsComponent
        get() = SettingsComponent(this)

    private fun child(config: RootConfig, componentContext: ComponentContext) =
        when (config) {
            RootConfig.Planets -> RootComponentChild.PlanetsChild(planetsComponent)
            is RootConfig.Planet -> RootComponentChild.PlanetChild(planetComponent(config.planet))
            RootConfig.AboutApp -> RootComponentChild.AboutAppChild(aboutAppComponent)
            RootConfig.Settings -> RootComponentChild.SettingsChild(settingsComponent)
        }
}