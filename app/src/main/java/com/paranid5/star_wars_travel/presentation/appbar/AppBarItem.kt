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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paranid5.star_wars_travel.navigation.component.RootConfig
import com.paranid5.star_wars_travel.navigation.composition_locals.LocalNavigator
import com.paranid5.star_wars_travel.resources.ui.StarWarsYellow

@Composable
fun AppBarItem(
    title: String,
    image: ImageVector,
    screen: RootConfig,
    modifier: Modifier = Modifier
) = AppBarItemImpl(
    screen = screen,
    modifier = modifier,
    icon = { AppBarIcon(title, image) }
)

@Composable
fun AppBarItem(
    title: String,
    image: Painter,
    screen: RootConfig,
    modifier: Modifier = Modifier
) = AppBarItemImpl(
    screen = screen,
    modifier = modifier,
    icon = { AppBarIcon(title, image) }
)

@Composable
private fun AppBarIcon(
    title: String,
    image: ImageVector,
    modifier: Modifier = Modifier
) = Column(modifier) {
    Icon(
        imageVector = image,
        contentDescription = title,
        tint = StarWarsYellow,
        modifier = Modifier
            .size(24.dp)
            .align(Alignment.CenterHorizontally)
    )

    Spacer(Modifier.height(8.dp))

    Text(
        text = title,
        color = StarWarsYellow,
        fontSize = 12.sp
    )
}

@Composable
private fun AppBarIcon(
    title: String,
    image: Painter,
    modifier: Modifier = Modifier
) = Column(modifier) {
    Icon(
        painter = image,
        contentDescription = title,
        tint = StarWarsYellow,
        modifier = Modifier
            .size(24.dp)
            .align(Alignment.CenterHorizontally)
    )

    Spacer(Modifier.height(8.dp))

    Text(
        text = title,
        color = StarWarsYellow,
        fontSize = 12.sp
    )
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