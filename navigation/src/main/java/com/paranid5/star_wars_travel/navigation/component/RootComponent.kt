package com.paranid5.star_wars_travel.navigation.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.paranid5.star_wars_travel.core.common.presentation.ThemeProvider
import com.paranid5.star_wars_travel.data.PlanetsRepository
import com.paranid5.star_wars_travel.impl.presentation.PlanetUiState

class RootComponent(
    componentContext: ComponentContext,
    private val planetsRepository: PlanetsRepository,
    private val themeProvider: ThemeProvider
) : ComponentContext by componentContext {
    private val navigation = StackNavigation<RootConfig>()

    val stack: Value<ChildStack<RootConfig, RootComponentChild>> =
        childStack(
            source = navigation,
            serializer = RootConfig.serializer(),
            initialConfiguration = RootConfig.Planets,
            handleBackButton = true,
            childFactory = ::child
        )

    fun navigateToPlanets() =
        navigation.bringToFront(previousPlanetsConfigOrDefault())

    fun navigateToPlanet(planet: PlanetUiState) =
        navigation.bringToFront(RootConfig.Planet(planet))

    fun navigateToSettings() =
        navigation.bringToFront(RootConfig.Settings)

    fun navigateToAboutApp() =
        navigation.bringToFront(RootConfig.AboutApp)

    infix fun navigateTo(screen: RootConfig) =
        when (screen) {
            RootConfig.Planets -> navigateToPlanets()
            is RootConfig.Planet -> navigateToPlanet(screen.planet)
            RootConfig.Settings -> navigateToSettings()
            RootConfig.AboutApp -> navigateToAboutApp()
        }

    private fun child(config: RootConfig, componentContext: ComponentContext) =
        when (config) {
            RootConfig.Planets -> RootComponentChild.PlanetsChild(
                PlanetsComponent(planetsRepository, componentContext)
            )

            is RootConfig.Planet -> RootComponentChild.PlanetChild(
                PlanetComponent(config.planet, planetsRepository, componentContext)
            )

            RootConfig.AboutApp -> RootComponentChild.AboutAppChild(
                AboutAppComponent(componentContext)
            )

            RootConfig.Settings -> RootComponentChild.SettingsChild(
                SettingsComponent(themeProvider, componentContext)
            )
        }

    private fun previousPlanetsConfigOrDefault() =
        stack
            .value
            .backStack
            .asReversed()
            .firstOrNull {
                it.instance is RootComponentChild.PlanetsChild
                        || it.instance is RootComponentChild.PlanetChild
            }
            ?.configuration
            ?: RootConfig.Planets
}