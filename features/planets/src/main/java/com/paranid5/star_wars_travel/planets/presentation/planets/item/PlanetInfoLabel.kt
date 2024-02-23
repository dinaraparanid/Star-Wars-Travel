package com.paranid5.star_wars_travel.planets.presentation.planets.item

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.paranid5.star_wars_travel.resources.ui.LocalAppColors

@Composable
fun PlanetInfoLabel(
    info: String,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Ellipsis
) {
    val colors = LocalAppColors.current

    Text(
        text = info,
        modifier = modifier,
        color = colors.onBackground,
        fontSize = 10.sp,
        fontFamily = FontFamily.SansSerif,
        maxLines = maxLines,
        overflow = overflow,
        style = TextStyle(
            platformStyle = PlatformTextStyle(
                includeFontPadding = false
            )
        )
    )
}