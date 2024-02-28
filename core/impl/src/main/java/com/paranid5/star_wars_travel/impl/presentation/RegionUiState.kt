package com.paranid5.star_wars_travel.impl.presentation

import androidx.compose.runtime.Immutable

@Immutable
data class RegionUiState(
    val region: String,
    val isSelected: Boolean
)
