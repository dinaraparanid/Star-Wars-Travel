package com.paranid5.star_wars_travel.planets.presentation.planets.item

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.paranid5.star_wars_travel.resources.R
import com.paranid5.star_wars_travel.resources.ui.LocalAppColors

@Composable
fun PlanetRegion(planetMainRegion: String?, modifier: Modifier = Modifier) {
    val colors = LocalAppColors.current

    Text(
        text = planetMainRegion ?: stringResource(R.string.secret_location),
        modifier = modifier,
        color = colors.onBackground,
        fontSize = 8.sp,
        fontFamily = FontFamily.SansSerif
    )
}