package com.paranid5.star_wars_travel.planets.presentation.planet

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paranid5.star_wars_travel.planets.presentation.HeaderText
import com.paranid5.star_wars_travel.resources.R
import com.paranid5.star_wars_travel.resources.ui.LocalAppColors
import com.paranid5.star_wars_travel.resources.ui.TransparentUtility

@Composable
fun Description(
    planetDescription: String,
    modifier: Modifier = Modifier
) {
    val colors = LocalAppColors.current

    var isDescriptionShown by remember {
        mutableStateOf(false)
    }

    val maxLines by remember(isDescriptionShown) {
        derivedStateOf { if (isDescriptionShown) Int.MAX_VALUE else 3 }
    }

    val textMeasurer = rememberTextMeasurer()

    val res = textMeasurer.measure(
        text = planetDescription,
        style = TextStyle(
            fontSize = 14.sp,
            platformStyle = PlatformTextStyle(
                includeFontPadding = false
            )
        ),
    )

    Column(modifier) {
        HeaderText(text = stringResource(R.string.description))

        Spacer(Modifier.height(16.dp))

        Text(
            text = planetDescription,
            color = colors.onBackground,
            fontSize = 14.sp,
            overflow = TextOverflow.Ellipsis,
            maxLines = maxLines,
            style = TextStyle(
                platformStyle = PlatformTextStyle(
                    includeFontPadding = false
                )
            ),
            modifier = Modifier.animateContentSize()
        )

        if (res.lineCount <= 3) {
            Spacer(Modifier.height(4.dp))

            Text(
                text = stringResource(
                    when {
                        isDescriptionShown -> R.string.hide
                        else -> R.string.show
                    }
                ),
                color = TransparentUtility,
                fontSize = 10.sp,
                modifier = Modifier.clickable {
                    isDescriptionShown = !isDescriptionShown
                }
            )
        }
    }
}