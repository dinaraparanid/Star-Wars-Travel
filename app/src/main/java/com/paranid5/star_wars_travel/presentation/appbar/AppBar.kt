package com.paranid5.star_wars_travel.presentation.appbar

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.paranid5.star_wars_travel.navigation.component.RootConfig
import com.paranid5.star_wars_travel.resources.R
import com.paranid5.star_wars_travel.resources.ui.AppBarColor

@Composable
fun AppBar(modifier: Modifier = Modifier) =
    BottomAppBar(
        containerColor = AppBarColor,
        modifier = modifier
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
    ) {
        AppBarItem(
            title = stringResource(R.string.planets),
            image = painterResource(R.drawable.planet),
            screen = RootConfig.Planets,
            modifier = Modifier.weight(1F),
            screenMatches = { it is RootConfig.Planets || it is RootConfig.Planet }
        )

        AppBarItem(
            title = stringResource(R.string.settings),
            image = painterResource(R.drawable.settings),
            screen = RootConfig.Settings,
            modifier = Modifier.weight(1F)
        )

        AppBarItem(
            title = stringResource(R.string.about_app),
            image = painterResource(R.drawable.question),
            screen = RootConfig.AboutApp,
            modifier = Modifier.weight(1F)
        )
    }
