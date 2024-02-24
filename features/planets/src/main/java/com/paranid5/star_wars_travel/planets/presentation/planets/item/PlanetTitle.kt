package com.paranid5.star_wars_travel.planets.presentation.planets.item

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.paranid5.star_wars_travel.resources.ui.LocalAppColors
import com.paranid5.star_wars_travel.resources.ui.starJediFont

@Composable
fun PlanetTitle(
    planetTitle: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 14.sp
) {
    val colors = LocalAppColors.current

    Text(
        text = planetTitle,
        modifier = modifier,
        color = colors.onBackground,
        fontSize = fontSize,
        fontFamily = starJediFont,
        style = TextStyle(
            platformStyle = PlatformTextStyle(
                includeFontPadding = false
            )
        )
    )
}