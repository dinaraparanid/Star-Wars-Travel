package com.paranid5.star_wars_travel.planets.presentation.planets.item

import android.content.Context
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.size.Precision
import coil.size.Scale
import com.paranid5.star_wars_travel.impl.presentation.PlanetUiState
import com.paranid5.star_wars_travel.resources.R

@Composable
internal fun PlanetCover(
    planet: PlanetUiState,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    AsyncImage(
        model = planetCoverModel(planet.coverUrl, context),
        contentDescription = stringResource(R.string.planet_preview),
        alignment = Alignment.Center,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(80.dp)
            .clip(RoundedCornerShape(8.dp))
    )
}

private fun planetCoverModel(
    planetCoverUrl: String?,
    context: Context,
    animationMillis: Int = 400
) = ImageRequest.Builder(context)
    .data(planetCoverUrl)
    .error(R.drawable.deathstar)
    .fallback(R.drawable.deathstar)
    .networkCachePolicy(CachePolicy.ENABLED)
    .diskCachePolicy(CachePolicy.ENABLED)
    .memoryCachePolicy(CachePolicy.ENABLED)
    .precision(Precision.EXACT)
    .scale(Scale.FILL)
    .crossfade(animationMillis)
    .build()