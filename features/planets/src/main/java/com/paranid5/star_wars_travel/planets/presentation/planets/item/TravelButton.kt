package com.paranid5.star_wars_travel.planets.presentation.planets.item

import android.widget.Toast
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paranid5.star_wars_travel.impl.presentation.PlanetUiState
import com.paranid5.star_wars_travel.resources.R
import com.paranid5.star_wars_travel.resources.ui.StarWarsYellow

@Composable
fun TravelButton(planet: PlanetUiState, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val onClickMessage = stringResource(R.string.enjoy_journey_to, planet.title)

    Button(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = StarWarsYellow,
            contentColor = StarWarsYellow
        ),
        onClick = {
            Toast.makeText(context, onClickMessage, Toast.LENGTH_LONG).show()
        }
    ) {
        Icon(
            painter = painterResource(R.drawable.falcon),
            contentDescription = stringResource(R.string.travel),
            tint = Color.Black,
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.CenterVertically)
        )

        Spacer(Modifier.width(8.dp))

        Text(
            text = stringResource(R.string.travel),
            modifier = Modifier.align(Alignment.CenterVertically),
            color = Color.Black,
            fontSize = 12.sp,
            fontFamily = FontFamily.SansSerif,
            style = TextStyle(
                platformStyle = PlatformTextStyle(
                    includeFontPadding = false
                )
            )
        )
    }
}