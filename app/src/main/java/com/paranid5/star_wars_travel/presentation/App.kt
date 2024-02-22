package com.paranid5.star_wars_travel.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.paranid5.star_wars_travel.presentation.appbar.AppBar
import com.paranid5.star_wars_travel.resources.ui.LocalAppColors

@Composable
fun App(modifier: Modifier = Modifier) {
    Box(modifier) {
        ScreenScaffold(Modifier.fillMaxSize())
    }
}

@Composable
private fun ScreenScaffold(modifier: Modifier = Modifier) {
    val backgroundColor = LocalAppColors.current.background

    Scaffold(
        modifier = modifier,
        bottomBar = { AppBar() },
        contentColor = backgroundColor,
        content = { ContentScreen(paddingValues = it) }
    )
}