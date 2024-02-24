package com.paranid5.star_wars_travel.planets.presentation.planet

import androidx.lifecycle.ViewModel
import com.paranid5.star_wars_travel.core.common.domain.entities.wookiepedia.Interest as InterestEntity
import com.paranid5.star_wars_travel.data.PlanetsRepository
import com.paranid5.star_wars_travel.impl.presentation.Interest

class PlanetViewModel(private val planetsRepository: PlanetsRepository) : ViewModel() {
    fun updateInterestsAsync(interests: List<Interest>) =
        planetsRepository.updateInterestsAsync(
            interests.map { InterestEntity(it.value, it.coverUrl) }
        )
}