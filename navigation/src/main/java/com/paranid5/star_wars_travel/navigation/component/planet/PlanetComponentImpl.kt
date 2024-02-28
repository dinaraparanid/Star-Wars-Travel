package com.paranid5.star_wars_travel.navigation.component.planet

import com.arkivanov.decompose.ComponentContext
import com.paranid5.star_wars_travel.core.common.domain.entities.wookiepedia.Interest as InterestEntity
import com.paranid5.star_wars_travel.data.PlanetsRepository
import com.paranid5.star_wars_travel.impl.presentation.Interest
import com.paranid5.star_wars_travel.impl.presentation.PlanetUiState

class PlanetComponentImpl(
    override val planet: PlanetUiState,
    private val planetsRepository: PlanetsRepository,
    componentContext: ComponentContext
) : ComponentContext by componentContext, PlanetComponent {
    override fun updateInterestsAsync(interests: List<Interest>) =
        planetsRepository.updateInterestsAsync(
            interests.map { InterestEntity(it.value, it.coverUrl) }
        )
}