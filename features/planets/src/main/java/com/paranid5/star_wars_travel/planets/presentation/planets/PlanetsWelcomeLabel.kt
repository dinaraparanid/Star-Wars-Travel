package com.paranid5.star_wars_travel.planets.presentation.planets

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.paranid5.star_wars_travel.resources.R
import com.paranid5.star_wars_travel.resources.ui.LocalAppColors

@Composable
fun PlanetsWelcomeLabel(modifier: Modifier = Modifier) {
    val colors = LocalAppColors.current

    Text(
        text = stringResource(R.string.planets_welcome_label),
        modifier = modifier,
        color = colors.onBackground,
        fontSize = 24.sp,
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        style = TextStyle(
            platformStyle = PlatformTextStyle(
                includeFontPadding = false
            )
        )
    )
}