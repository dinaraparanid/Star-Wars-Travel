package com.paranid5.star_wars_travel.planets.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
internal fun Label(text: String, modifier: Modifier = Modifier) =
    HeaderText(text = text, modifier = modifier, textSize = 14.sp)