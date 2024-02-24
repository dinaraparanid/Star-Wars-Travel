package com.paranid5.star_wars_travel.planets.presentation

import android.content.Context
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.size.Precision
import coil.size.Scale
import com.paranid5.star_wars_travel.resources.R

internal fun coverModel(
    coverUrl: String?,
    context: Context,
    animationMillis: Int = 400,
    isPlaceholderRequired: Boolean = false
) = ImageRequest.Builder(context)
    .data(coverUrl)
    .run {
        when {
            isPlaceholderRequired -> placeholder(R.drawable.deathstar)
            else -> this
        }
    }
    .error(R.drawable.deathstar)
    .fallback(R.drawable.deathstar)
    .networkCachePolicy(CachePolicy.ENABLED)
    .diskCachePolicy(CachePolicy.ENABLED)
    .memoryCachePolicy(CachePolicy.ENABLED)
    .precision(Precision.EXACT)
    .scale(Scale.FILL)
    .crossfade(animationMillis)
    .build()