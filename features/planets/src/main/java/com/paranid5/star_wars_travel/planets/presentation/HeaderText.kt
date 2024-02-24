package com.paranid5.star_wars_travel.planets.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.paranid5.star_wars_travel.resources.ui.LocalAppColors

@Composable
fun HeaderText(
    text: String,
    modifier: Modifier = Modifier,
    textSize: TextUnit = 20.sp
) {
    val colors = LocalAppColors.current

    Text(
        text = text,
        color = colors.onBackground,
        fontSize = textSize,
        modifier = modifier
    )
}