package com.paranid5.star_wars_travel.navigation.component.planet

import com.paranid5.star_wars_travel.impl.presentation.Interest
import com.paranid5.star_wars_travel.impl.presentation.PlanetUiState
import kotlinx.coroutines.Job

interface PlanetComponent {
    val planet: PlanetUiState

    fun updateInterestsAsync(interests: List<Interest>): Job
}