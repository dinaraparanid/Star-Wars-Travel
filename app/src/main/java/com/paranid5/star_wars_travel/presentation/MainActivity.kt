package com.paranid5.star_wars_travel.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.defaultComponentContext
import com.paranid5.star_wars_travel.core.common.presentation.ThemeProvider
import com.paranid5.star_wars_travel.data.PlanetsRepository
import com.paranid5.star_wars_travel.navigation.component.RootComponent
import com.paranid5.star_wars_travel.navigation.composition_locals.LocalNavigator
import com.paranid5.star_wars_travel.presentation.composition_locals.LocalActivity
import com.paranid5.star_wars_travel.presentation.ui.theme.StarWarsTravelTheme
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainActivity : ComponentActivity(), KoinComponent {
    private val planetsRepository by inject<PlanetsRepository>()
    private val themeProvider by inject<ThemeProvider>()

    private inline val rootComponent
        get() = RootComponent(
            componentContext = defaultComponentContext(),
            planetsRepository = planetsRepository,
            themeProvider = themeProvider
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        val root = rootComponent

        setContent {
            val theme by themeProvider.themeState.collectAsState()

            CompositionLocalProvider(
                LocalActivity provides this,
                LocalNavigator provides root,
            ) {
                StarWarsTravelTheme(theme) {
                    App(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}