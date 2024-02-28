package com.paranid5.star_wars_travel.planets.presentation.planets.item

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.paranid5.star_wars_travel.resources.ui.StarJediFont

@Composable
internal fun PlanetTitle(
    planetTitle: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 14.sp
) = Text(
    text = planetTitle,
    modifier = modifier,
    color = Color.White,
    fontSize = fontSize,
    fontFamily = StarJediFont,
    style = TextStyle(
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    )
)