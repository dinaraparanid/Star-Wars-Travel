package com.paranid5.star_wars_travel.planets.di

import com.paranid5.star_wars_travel.data.di.dataModule
import com.paranid5.star_wars_travel.planets.presentation.planets.PlanetsViewModel
import com.paranid5.star_wars_travel.planets.presentation.planet.PlanetViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val planetsModule = module {
    includes(dataModule)
    viewModelOf(::PlanetsViewModel)
    viewModelOf(::PlanetViewModel)
}