package com.paranid5.star_wars_travel.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.stackAnimation
import com.paranid5.star_wars_travel.about_app.presentation.AboutAppScreen
import com.paranid5.star_wars_travel.navigation.component.RootComponentChild
import com.paranid5.star_wars_travel.navigation.composition_locals.LocalNavigator
import com.paranid5.star_wars_travel.planets.presentation.planet.PlanetScreen
import com.paranid5.star_wars_travel.planets.presentation.planets.PlanetsScreen
import com.paranid5.star_wars_travel.settings.presentation.SettingsScreen

@Composable
fun ContentScreen(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
) {
    val navigator = LocalNavigator.current!!
    val direction = LocalLayoutDirection.current

    Children(
        stack = navigator.stack,
        animation = stackAnimation(fade()),
        modifier = modifier.padding(
            top = 0.dp,
            bottom = paddingValues.calculateBottomPadding(),
            start = paddingValues.calculateStartPadding(direction),
            end = paddingValues.calculateEndPadding(direction)
        )
    ) {
        when (val child = it.instance) {
            is RootComponentChild.PlanetsChild -> PlanetsScreen(
                planetsComponent = child.component,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = paddingValues.calculateTopPadding() + 8.dp,
                        start = 16.dp,
                        end = 16.dp
                    )
            )

            is RootComponentChild.PlanetChild -> PlanetScreen(
                planet = child.component.planet,
                planetComponent = child.component,
                modifier = Modifier.fillMaxSize()
            )

            is RootComponentChild.AboutAppChild -> AboutAppScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = paddingValues.calculateTopPadding() + 8.dp,
                        start = 8.dp,
                        end = 8.dp
                    )
            )

            is RootComponentChild.SettingsChild -> SettingsScreen(
                settingsComponent = child.component,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = paddingValues.calculateTopPadding() + 8.dp,
                        start = 8.dp,
                        end = 8.dp
                    )
            )
        }
    }
}