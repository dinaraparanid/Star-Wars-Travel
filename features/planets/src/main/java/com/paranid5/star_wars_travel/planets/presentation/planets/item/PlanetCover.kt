package com.paranid5.star_wars_travel.planets.presentation.planets.item

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.paranid5.star_wars_travel.impl.presentation.PlanetUiState
import com.paranid5.star_wars_travel.planets.presentation.coverModel
import com.paranid5.star_wars_travel.resources.R

@Composable
fun PlanetCover(
    planet: PlanetUiState,
    modifier: Modifier = Modifier,
    roundedCorners: Dp = 8.dp
) {
    val context = LocalContext.current

    AsyncImage(
        model = coverModel(planet.coverUrl, context),
        contentDescription = stringResource(R.string.planet_preview),
        alignment = Alignment.Center,
        contentScale = ContentScale.Crop,
        modifier = modifier.clip(RoundedCornerShape(roundedCorners))
    )
}