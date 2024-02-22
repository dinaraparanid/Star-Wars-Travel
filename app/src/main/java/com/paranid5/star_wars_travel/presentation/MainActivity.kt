package com.paranid5.star_wars_travel.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.defaultComponentContext
import com.paranid5.star_wars_travel.navigation.component.RootNavigator
import com.paranid5.star_wars_travel.presentation.composition_locals.LocalActivity
import com.paranid5.star_wars_travel.presentation.composition_locals.LocalNavigator
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

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StarWarsTravelTheme {
        Greeting("Android")
    }
}