package com.paranid5.star_wars_travel.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.stackAnimation
import com.paranid5.star_wars_travel.about_app.presentation.AboutAppScreen
import com.paranid5.star_wars_travel.navigation.component.RootComponentChild
import com.paranid5.star_wars_travel.planets.presentation.PlanetScreen
import com.paranid5.star_wars_travel.planets.presentation.PlanetsScreen
import com.paranid5.star_wars_travel.presentation.composition_locals.LocalNavigator
import com.paranid5.star_wars_travel.settings.presentation.SettingsScreen

@Composable
fun ContentScreen(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier
) {
    val navigator = LocalNavigator.current!!

    Children(
        stack = navigator.stack,
        animation = stackAnimation(fade()),
        modifier = modifier.padding(
            top = paddingValues.calculateTopPadding(),
            bottom = paddingValues.calculateBottomPadding()
        )
    ) {
        println(it.instance)

        when (it.instance) {
            is RootComponentChild.PlanetsChild -> PlanetsScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 8.dp, start = 8.dp, end = 8.dp)
            )

            is RootComponentChild.PlanetChild -> PlanetScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 8.dp, start = 8.dp, end = 8.dp)
            )

            is RootComponentChild.AboutAppChild -> AboutAppScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 8.dp, start = 8.dp, end = 8.dp)
            )

            is RootComponentChild.SettingsChild -> SettingsScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 8.dp, start = 8.dp, end = 8.dp)
            )
        }
    }
}