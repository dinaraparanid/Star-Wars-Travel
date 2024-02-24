package com.paranid5.star_wars_travel.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.defaultComponentContext
import com.paranid5.star_wars_travel.navigation.component.RootNavigator
import com.paranid5.star_wars_travel.navigation.composition_locals.LocalNavigator
import com.paranid5.star_wars_travel.presentation.composition_locals.LocalActivity
import com.paranid5.star_wars_travel.presentation.ui.theme.StarWarsTravelTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            StarWarsTravelTheme {
                CompositionLocalProvider(
                    LocalActivity provides this,
                    LocalNavigator provides RootNavigator(defaultComponentContext()),
                ) {
                    App(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}