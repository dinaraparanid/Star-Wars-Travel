package com.paranid5.star_wars_travel.presentation.appbar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.paranid5.star_wars_travel.navigation.component.RootConfig
import com.paranid5.star_wars_travel.navigation.composition_locals.LocalNavigator
import com.paranid5.star_wars_travel.resources.ui.StarWarsHologram
import com.paranid5.star_wars_travel.resources.ui.StarWarsYellow

@Composable
fun AppBarItem(
    title: String,
    image: ImageVector,
    screen: RootConfig,
    modifier: Modifier = Modifier,
    screenMatches: (RootConfig) -> Boolean = { it == screen }
) = AppBarItemImpl(
    screen = screen,
    modifier = modifier,
    icon = { AppBarIcon(title, image, screenMatches) }
)

@Composable
fun AppBarItem(
    title: String,
    image: Painter,
    screen: RootConfig,
    modifier: Modifier = Modifier,
    screenMatches: (RootConfig) -> Boolean = { it == screen }
) = AppBarItemImpl(
    screen = screen,
    modifier = modifier,
    icon = { AppBarIcon(title, image, screenMatches) }
)

@Composable
private fun AppBarIcon(
    title: String,
    image: ImageVector,
    screenMatches: (RootConfig) -> Boolean,
    modifier: Modifier = Modifier
) {
    val itemColor by rememberItemColor(screenMatches)

    Column(modifier) {
        Icon(
            imageVector = image,
            contentDescription = title,
            tint = itemColor,
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = title,
            color = itemColor,
            fontSize = 12.sp
        )
    }
}

@Composable
private fun AppBarIcon(
    title: String,
    image: Painter,
    screenMatches: (RootConfig) -> Boolean,
    modifier: Modifier = Modifier
) {
    val itemColor by rememberItemColor(screenMatches)

    Column(modifier) {
        Icon(
            painter = image,
            contentDescription = title,
            tint = itemColor,
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = title,
            color = itemColor,
            fontSize = 12.sp
        )
    }
}

@Composable
private fun AppBarItemImpl(
    screen: RootConfig,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    val navigator = LocalNavigator.current!!

    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent,
        ),
        elevation = null,
        shape = RoundedCornerShape(20.dp),
        onClick = { navigator navigateTo screen },
        content = { icon() }
    )
}

@Composable
private fun rememberItemColor(screenMatches: (RootConfig) -> Boolean): State<Color> {
    val navigator = LocalNavigator.current!!
    val stack by navigator.stack.subscribeAsState()

    val isScreenCurrent by remember(stack) {
        derivedStateOf { screenMatches(stack.active.configuration) }
    }

    return remember(isScreenCurrent) {
        derivedStateOf { if (isScreenCurrent) StarWarsHologram else StarWarsYellow }
    }
}