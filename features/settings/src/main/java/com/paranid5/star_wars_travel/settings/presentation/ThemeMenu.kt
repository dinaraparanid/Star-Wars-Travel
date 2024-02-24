package com.paranid5.star_wars_travel.settings.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paranid5.star_wars_travel.core.common.presentation.Theme
import com.paranid5.star_wars_travel.resources.R
import com.paranid5.star_wars_travel.resources.ui.LocalAppColors

@Composable
fun ThemeMenu(
    viewModel: SettingsViewModel,
    modifier: Modifier = Modifier
) {
    val theme by viewModel.themeState.collectAsState()

    when (theme) {
        Theme.LIGHT -> ThemeMenuImpl(
            iconPainter = painterResource(R.drawable.light_mode),
            text = stringResource(R.string.light_mode),
            modifier = modifier.clickable { viewModel.resetTheme() }
        )

        Theme.DARK -> ThemeMenuImpl(
            iconPainter = painterResource(R.drawable.dark_mode),
            text = stringResource(R.string.dark_mode),
            modifier = modifier.clickable { viewModel.resetTheme() }
        )
    }
}

@Composable
private fun ThemeMenuImpl(
    iconPainter: Painter,
    text: String,
    modifier: Modifier = Modifier
) {
    val colors = LocalAppColors.current

    Row(modifier) {
        Image(
            painter = iconPainter,
            contentDescription = text,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .size(32.dp)
                .padding(start = 4.dp)
                .align(Alignment.CenterVertically)
        )

        Spacer(Modifier.width(8.dp))

        Text(
            text = text,
            color = colors.onBackground,
            fontSize = 16.sp,
            modifier = Modifier.padding(top = 6.dp, bottom = 4.dp)
        )
    }
}