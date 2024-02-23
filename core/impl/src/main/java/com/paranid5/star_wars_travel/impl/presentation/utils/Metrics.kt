package com.paranid5.star_wars_travel.impl.presentation.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

@Composable
fun Dp.toPx() =
    with(LocalDensity.current) { this@toPx.toPx() }

@Composable
fun Int.pxToDp() =
    with(LocalDensity.current) { this@pxToDp.toDp() }