package com.paranid5.star_wars_travel.presentation.appbar

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.paranid5.star_wars_travel.navigation.component.RootComponentChild
import com.paranid5.star_wars_travel.presentation.composition_locals.LocalNavigator
import com.paranid5.star_wars_travel.resources.R
import com.paranid5.star_wars_travel.resources.ui.LocalAppColors

@Composable
fun AppBar(modifier: Modifier = Modifier) {
    val colors = LocalAppColors.current
    val navigator = LocalNavigator.current!!

    BottomAppBar(
        contentColor = colors.primary,
        modifier = modifier
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
    ) {
        AppBarItem(
            title = stringResource(R.string.planets),
            image = painterResource(R.drawable.planet),
            screen = RootComponentChild.PlanetsChild(navigator.planetsComponent),
            modifier = Modifier.weight(1F)
        )

        AppBarItem(
            title = stringResource(R.string.settings),
            image = painterResource(R.drawable.settings),
            screen = RootComponentChild.SettingsChild(navigator.settingsComponent),
            modifier = Modifier.weight(1F)
        )

        AppBarItem(
            title = stringResource(R.string.about_app),
            image = painterResource(R.drawable.question),
            screen = RootComponentChild.AboutAppChild(navigator.aboutAppComponent),
            modifier = Modifier.weight(1F)
        )
    }
}
