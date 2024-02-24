package com.paranid5.star_wars_travel.planets.presentation.planets.item

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.paranid5.star_wars_travel.core.common.domain.use_case.prettifyNumber

@Composable
fun PlanetInfoLabel(
    info: String,
    modifier: Modifier = Modifier,
    maxLines: Int = 1,
    overflow: TextOverflow = TextOverflow.Ellipsis
) {
    Text(
        text = prettifyNumber(info),
        modifier = modifier,
        color = Color.White,
        fontSize = 12.sp,
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